package forms;

import controls.ContaBancaria;
import controls.ContaBancariaMovimento;
import controls.ContaContabil;
import controls.ContaContabilMovimento;
import controls.RelContabilidadeBanco;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

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
        //geraListaDeMovimentoContaContabil();
        geraListaDeMovimentoContaBancaria();
    }

    private void geraListaDeRelacionamentos() {
        List<String> ordem = new ArrayList<>();
        ordem.add("descricao");
        RelContabilidadeBanco consulta = new RelContabilidadeBanco();
        setListaDeRelacionamentos((List) consulta.obter(null, null, ordem));

    }
//
//    private void geraListaDeMovimentoContaContabil() {
//        List<String> ordem = new ArrayList<>();
//        ordem.add("dataMov");
//        ordem.add("idcontaContabilMovimento");
//        ContaContabilMovimento consulta = new ContaContabilMovimento();
//        listaDeMovimentoContaContabil = (List) consulta.obter(null, null, ordem);
//    }

    private void geraListaDeMovimentoContaBancaria() {
        List<String> ordem = new ArrayList<>();
        ordem.add("dataMov");
        ordem.add("idcontaBancariaMovimento");
        ContaBancariaMovimento consulta = new ContaBancariaMovimento();
        listaDeMovimentoContaBancaria = (List) consulta.obter(null, null, ordem);
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

        List<String> atributo = new ArrayList<>();
        atributo.add("relContabilidadeBanco");

        List<Object> valor = new ArrayList<>();
        valor.add(relacionamento);

        List<String> ordem = new ArrayList<>();
        ordem.add("idcontaBancaria");

        return (List) consulta.obter(atributo, valor, ordem);

    }

    private List<ContaContabil> getContasContabeis() {

        ContaContabil consulta = new ContaContabil();

        List<String> atributo = new ArrayList<>();
        atributo.add("relContabilidadeBanco");

        List<Object> valor = new ArrayList<>();
        valor.add(relacionamento);

        List<String> ordem = new ArrayList<>();
        ordem.add("idcontaContabil");

        return (List) consulta.obter(atributo, valor, ordem);

    }

    private void filtraMovimentosContasContabeis() {

        List<ContaContabil> contasContabeis = getContasContabeis();

        ContaContabilMovimento consulta = new ContaContabilMovimento();

        List<String> atributo = new ArrayList<>();
        atributo.add("conta");

        List<Object> valor = new ArrayList<>();
        valor.add(contasContabeis);
        
        List<String> ordem = new ArrayList<>();
        ordem.add("dataMov");
        ordem.add("idcontaContabilMovimento");

        listaDeMovimentoContaContabil = (List) consulta.obter(atributo, valor, ordem);

    }

    private void filtraMovimentosContasBancarias() {

        List<ContaBancaria> contasBancarias = getContasBancarias();

    }

}
