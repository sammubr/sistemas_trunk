package forms;

import controls.ContaBancaria;
import controls.ContaBancariaMovimento;
import controls.ContaContabil;
import controls.ContaContabilMovimento;
import controls.RelContabilidadeBanco;
import java.io.Serializable;
import java.math.BigDecimal;
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

    private List<ContaContabilMovimento> listaDeMovimentoContaContabil;
    private List<ContaContabilMovimento> listaDeMovimentoContaContabilSelecionados;

    private List<ContaBancariaMovimento> listaDeMovimentoContaBancaria;
    private List<ContaBancariaMovimento> listaDeMovimentoContaBancariaSelecionados;

    private BigDecimal saldoContaContabil;
    private BigDecimal saldoContaBancaria;
    
    private Date dataInicialConciliacao;
    private Date dataFinalConciliacao;

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

    public BigDecimal getSaldoContaContabil() {
        return saldoContaContabil;
    }

    public void setSaldoContaContabil(BigDecimal saldoContaContabil) {
        this.saldoContaContabil = saldoContaContabil;
    }

    public BigDecimal getSaldoContaBancaria() {
        return saldoContaBancaria;
    }

    public void setSaldoContaBancaria(BigDecimal saldoContaBancaria) {
        this.saldoContaBancaria = saldoContaBancaria;
    }

    public Date getDataInicialConciliacao() {
        return dataInicialConciliacao;
    }

    public void setDataInicialConciliacao(Date dataInicialConciliacao) {
        this.dataInicialConciliacao = dataInicialConciliacao;
    }

    public Date getDataFinalConciliacao() {
        return dataFinalConciliacao;
    }

    public void setDataFinalConciliacao(Date dataFinalConciliacao) {
        this.dataFinalConciliacao = dataFinalConciliacao;
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
        saldoContaContabil = apuraSaldoContaContabil();
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
        saldoContaBancaria = apuraSaldoContaBancaria();
    }

    private BigDecimal apuraSaldoContaContabil() {

        BigDecimal saldo = new BigDecimal(0);

        for (ContaContabil conta : relacionamento.getContaContabilCollection()) {
            saldo = saldo.add(getSaldoContaContabil(listaDeMovimentoContaContabil, conta));
        }

        return saldo;
    }

    public BigDecimal getSaldoContaContabil(List<ContaContabilMovimento> lista, ContaContabil filtro) {

        BigDecimal saldo = new BigDecimal(0);

        for (ContaContabilMovimento item : lista) {
            if (item.getConta().equals(filtro)) {
                saldo = (item.getSaldo());
            }
        }
        return saldo;
    }

    private BigDecimal apuraSaldoContaBancaria() {

        BigDecimal saldo = new BigDecimal(0);

        for (ContaBancaria conta : relacionamento.getContaBancariaCollection()) {
            saldo = saldo.add(getSaldoContaBancaria(listaDeMovimentoContaBancaria, conta));
        }

        return saldo;
    }

    public BigDecimal getSaldoContaBancaria(List<ContaBancariaMovimento> lista, ContaBancaria filtro) {

        BigDecimal saldo = new BigDecimal(0);

        for (ContaBancariaMovimento item : lista) {
            if (item.getConta().equals(filtro)) {
                saldo = (item.getSaldo());
            }
        }
        return saldo;
    }

}
