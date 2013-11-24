package forms;

import controls.ContaContabil;
import controls.ContaContabilMovimento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import util.JsfUtil;

@Named("conciliacaoManual")
@ViewScoped
public class ConciliacaoManual implements Serializable {

// ------------------------------------------------------------------ ATRIBUTOS
    private ContaContabilMovimento item;
    private List<ContaContabilMovimento> lista;
    private List<ContaContabilMovimento> itensSelecionados;
    private ContaContabil contaContabilSelecionada;
    private List<ContaContabil> listaDeContasContabeis;

// ---------------------------------------------------------------- CONSTRUCTOR    
    public ConciliacaoManual() {
        geraListaDeContasContabeis();
        geraListaDeMovimentos();
    }

    private void geraListaDeContasContabeis() {
        List<String> ordem = new ArrayList<>();
        ordem.add("descricao");
        ContaContabil contaContabil = new ContaContabil();
        setListaDeContasContabeis((List<ContaContabil>) (List) contaContabil.obter(null, null, ordem));
    }

// --------------------------------------------- GETTERS E SETTERS DESTA CLASSE
    public ContaContabilMovimento getItem() {
        return item;
    }

    public void setItem(ContaContabilMovimento item) {
        this.item = item;
    }

    public List<ContaContabilMovimento> getLista() {
        return lista;
    }

    public void setLista(List<ContaContabilMovimento> lista) {
        this.lista = lista;
    }

    public List<ContaContabilMovimento> getItensSelecionados() {
        return itensSelecionados;
    }

    public void setItensSelecionados(List<ContaContabilMovimento> itensSelecionados) {
        this.itensSelecionados = itensSelecionados;
    }

    public ContaContabil getContaContabilSelecionada() {
        return contaContabilSelecionada;
    }

    public void setContaContabilSelecionada(ContaContabil contaContabilSelecionada) {
        this.contaContabilSelecionada = contaContabilSelecionada;
    }

    public List<ContaContabil> getListaDeContasContabeis() {
        return listaDeContasContabeis;
    }

    public void setListaDeContasContabeis(List<ContaContabil> listaDeContasContabeis) {
        this.listaDeContasContabeis = listaDeContasContabeis;
    }

// ----------------------------------------------------- MÉTODOS PARA PERSISTIR
    public void criaNovo() {
        item = new ContaContabilMovimento();
    }

    public void edita(ContaContabilMovimento itemSelecionado) {
        item = itemSelecionado;
    }

    public void persiste() {
        getItem().setConta(contaContabilSelecionada);
        getItem().persiste();
        geraListaDeMovimentos();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"), "");
        RequestContext.getCurrentInstance().execute("$('#myModal').modal('hide')");
    }

    public void exclui() {
        switch (itensSelecionados.size()) {
            case 0:
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("EmptyRecordsToDelete"), "");
                break;
            case 1:
                for (ContaContabilMovimento itemSelecionado : itensSelecionados) {
                    itemSelecionado.exclui();
                }
                geraListaDeMovimentos();
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"), "");
                break;
            default:
                for (ContaContabilMovimento itemSelecionado : itensSelecionados) {
                    itemSelecionado.exclui();
                }
                geraListaDeMovimentos();
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordsDeleted"), "");
                break;
        }
    }

    private void geraListaDeMovimentos() {

        List<String> ordem = new ArrayList<>();
        ordem.add("dataMov");
        ordem.add("idcontaContabilMovimento");

        ContaContabilMovimento consulta = new ContaContabilMovimento();
        lista = (List) consulta.obter(null, null, ordem);
    }
}