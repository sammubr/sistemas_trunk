package forms;

import controls.ContaBancaria;
import controls.ContaBancariaMovimento;
import controls.ContaContabil;
import controls.ContaContabilMovimento;
import controls.RelContabilidadeBanco;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

@Named("conciliacaoManual")
@ViewScoped
public class ConciliacaoManual implements Serializable {

// ------------------------------------------------------------------ ATRIBUTOS
    private boolean gridVisivel = true;
    private RelContabilidadeBanco relacionamento;
    private List<RelContabilidadeBanco> listaDeRelacionamentos;
    private Date dataConciliacao;

    private List<ContaContabilMovimento> listaDeMovimentoContaContabil;
    private List<ContaContabilMovimento> listaDeMovimentoContaContabilSelecionados;

    private List<ContaBancariaMovimento> listaDeMovimentoContaBancaria;
    private List<ContaBancariaMovimento> listaDeMovimentoContaBancariaSelecionados;

// ---------------------------------------------------------------- CONSTRUCTOR    
    public ConciliacaoManual() {
        geraListaDeRelacionamentos();
    }

    private void geraListaDeRelacionamentos() {
        RelContabilidadeBanco consulta = new RelContabilidadeBanco();
        List<Order> ordem = new ArrayList<>();
        ordem.add(Order.asc("descricao"));
        setListaDeRelacionamentos(consulta.obterLista(null, ordem));
    }

// --------------------------------------------- GETTERS E SETTERS DESTA CLASSE
    public boolean isGridVisivel() {
        return gridVisivel;
    }

    public void setGridVisivel(boolean gridVisivel) {
        this.gridVisivel = gridVisivel;
    }

    public RelContabilidadeBanco getRelacionamento() {
        return relacionamento;
    }

    public void setRelacionamento(RelContabilidadeBanco relacionamento) {
        this.relacionamento = relacionamento;
    }

    public List<RelContabilidadeBanco> getListaDeRelacionamentos() {
        return listaDeRelacionamentos;
    }

    public void setListaDeRelacionamentos(List<RelContabilidadeBanco> listaDeRelacionamentos) {
        this.listaDeRelacionamentos = listaDeRelacionamentos;
    }

    public Date getDataConciliacao() {
        return dataConciliacao;
    }

    public void setDataConciliacao(Date dataConciliacao) {
        this.dataConciliacao = dataConciliacao;
    }

    public List<ContaContabilMovimento> getListaDeMovimentoContaContabil() {
        return listaDeMovimentoContaContabil;
    }

    public void setListaDeMovimentoContaContabil(List<ContaContabilMovimento> listaDeMovimentoContaContabil) {
        this.listaDeMovimentoContaContabil = listaDeMovimentoContaContabil;
    }

    public List<ContaContabilMovimento> getListaDeMovimentoContaContabilSelecionados() {
        return listaDeMovimentoContaContabilSelecionados;
    }

    public void setListaDeMovimentoContaContabilSelecionados(List<ContaContabilMovimento> listaDeMovimentoContaContabilSelecionados) {
        this.listaDeMovimentoContaContabilSelecionados = listaDeMovimentoContaContabilSelecionados;
    }

    public List<ContaBancariaMovimento> getListaDeMovimentoContaBancaria() {
        return listaDeMovimentoContaBancaria;
    }

    public void setListaDeMovimentoContaBancaria(List<ContaBancariaMovimento> listaDeMovimentoContaBancaria) {
        this.listaDeMovimentoContaBancaria = listaDeMovimentoContaBancaria;
    }

    public List<ContaBancariaMovimento> getListaDeMovimentoContaBancariaSelecionados() {
        return listaDeMovimentoContaBancariaSelecionados;
    }

    public void setListaDeMovimentoContaBancariaSelecionados(List<ContaBancariaMovimento> listaDeMovimentoContaBancariaSelecionados) {
        this.listaDeMovimentoContaBancariaSelecionados = listaDeMovimentoContaBancariaSelecionados;
    }

// ----------------------------------------------------- MÉTODOS PARA PERSISTIR
    public void filtraMovimentos() {
        filtraMovimentosContasContabeis();
        filtraMovimentosContasBancarias();
        setGridVisivel(false);
    }

    private List<ContaBancaria> getContasBancarias() {
        ContaBancaria consulta = new ContaBancaria();
        List<Criterion> filtro = new ArrayList<>();
        filtro.add(Restrictions.eq("relContabilidadeBanco", relacionamento));
        List<Order> ordem = new ArrayList<>();
        ordem.add(Order.asc("idcontaBancaria"));
        return consulta.obterLista(filtro, ordem);
    }

    private List<ContaContabil> getContasContabeis() {
        ContaContabil consulta = new ContaContabil();
        List<Criterion> filtro = new ArrayList<>();
        filtro.add(Restrictions.eq("relContabilidadeBanco", relacionamento));
        List<Order> ordem = new ArrayList<>();
        ordem.add(Order.asc("idcontaContabil"));
        return consulta.obterLista(filtro, ordem);
    }

    private void filtraMovimentosContasContabeis() {
        List<ContaContabil> contasContabeis = getContasContabeis();
        ContaContabilMovimento consulta = new ContaContabilMovimento();
        List<Criterion> filtro = new ArrayList<>();
        filtro.add(Restrictions.in("conta", contasContabeis));
        List<Order> ordem = new ArrayList<>();
        ordem.add(Order.asc("dataMov"));
        ordem.add(Order.asc("idcontaContabilMovimento"));
        listaDeMovimentoContaContabil = consulta.obterLista(filtro, ordem);

    }

    private void filtraMovimentosContasBancarias() {
        List<ContaBancaria> contasBancarias = getContasBancarias();
        ContaBancariaMovimento consulta = new ContaBancariaMovimento();
        List<Criterion> filtro = new ArrayList<>();
        filtro.add(Restrictions.in("conta", contasBancarias));
        List<Order> ordem = new ArrayList<>();
        ordem.add(Order.asc("dataMov"));
        ordem.add(Order.asc("idcontaBancariaMovimento"));
        listaDeMovimentoContaBancaria = consulta.obterLista(filtro, ordem);

    }
}
