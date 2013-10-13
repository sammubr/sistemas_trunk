/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import controls.ContaBancaria;
import controls.ContaContabil;
import controls.RelContabilidadeBanco;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.model.CollectionDataModel;
import javax.faces.model.DataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import util.JsfUtil;

@Named("relacionamentoCadastro")
@ViewScoped
public class RelacionamentoCadastro implements Serializable {

    private RelContabilidadeBanco fRelacionamento;
    private DataModel fListaDeRelacionamentos;
    private List<ContaBancaria> fListaDeContasBancarias;
    private List<ContaContabil> fListaDeContasContabeis;
    private boolean fGridVisivel;
    private boolean fItemVisivel;

    @PostConstruct
    public void abreForm() {
        geraListaDeRelacionamentos();
        mostraGrid();
    }

    public RelContabilidadeBanco getRelacionamento() {
        return fRelacionamento;
    }

    public void setRelacionamento(RelContabilidadeBanco relacionamento) {
        this.fRelacionamento = relacionamento;
    }

    public DataModel getListaDeRelacionamentos() {
        return fListaDeRelacionamentos;
    }

    public void setListaDeRelacionamentos(DataModel listaRelacionamentos) {
        this.fListaDeRelacionamentos = listaRelacionamentos;
    }

    public boolean isGridVisivel() {
        return fGridVisivel;
    }

    public void setGridVisivel(boolean gridVisivel) {
        this.fGridVisivel = gridVisivel;
    }

    public boolean isItemVisivel() {
        return fItemVisivel;
    }

    public void setItemVisivel(boolean itemVisivel) {
        this.fItemVisivel = itemVisivel;
    }

    public List<ContaContabil> getListaDeContasContabeis() {
        return fListaDeContasContabeis;
    }

    public void setListaDeContasContabeis(List<ContaContabil> listaDeContasContabeis) {
        this.fListaDeContasContabeis = listaDeContasContabeis;
    }

    public List<ContaBancaria> getListaDeContasBancarias() {
        return fListaDeContasBancarias;
    }

    public void setListaDeContasBancarias(List<ContaBancaria> listaDeContasBancarias) {
        this.fListaDeContasBancarias = listaDeContasBancarias;
    }

    private void geraListaDeRelacionamentos() {
        List<String> ordem = new ArrayList<>();
        ordem.add("descricao");
        RelContabilidadeBanco relacionamento = new RelContabilidadeBanco();
        this.fListaDeRelacionamentos = new CollectionDataModel(relacionamento.obter(null, null, ordem));
    }

    public void mostraGrid() {
        setGridVisivel(true);
        setItemVisivel(false);
    }

    public void mostraItem() {
        setGridVisivel(false);
        setItemVisivel(true);

    }

    public void criaNovo() {
        fRelacionamento = new RelContabilidadeBanco();
        fListaDeContasContabeis = new ArrayList<>();
        fListaDeContasBancarias = new ArrayList<>();
        mostraItem();
    }

    public void edita() {
        setRelacionamento((RelContabilidadeBanco) getListaDeRelacionamentos().getRowData());
        mostraItem();
    }

    public void persiste() {
        fRelacionamento.persiste();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"), "");
        geraListaDeRelacionamentos();
        mostraGrid();
    }

    public void exclui() {
        fRelacionamento = (RelContabilidadeBanco) getListaDeRelacionamentos().getRowData();
        fRelacionamento.exclui();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"), "");
        geraListaDeRelacionamentos();
    }

    public void addContaBancaria(ContaBancaria conta) {
        fListaDeContasBancarias.add(conta);
    }

    public void removeContaBancaria(ContaBancaria conta) {
        fListaDeContasBancarias.remove(conta);
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"), "");
    }

    public void addContaContabil(ContaContabil conta) {
        fListaDeContasContabeis.add(conta);
    }

    public void removeContaContabil(ContaContabil conta) {
        fListaDeContasContabeis.remove(conta);
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"), "");
    }

    public void chooseContaContabil() {
        RequestContext.getCurrentInstance().openDialog("contaContabil/selectContaContabil.xhtml");
    }

    public void chooseContaBancaria() {
        RequestContext.getCurrentInstance().openDialog("contaBancaria/selectContaBancaria.xhtml");
    }

    public void onContaBancariaChosen(SelectEvent event) {
        ContaBancaria conta = (ContaBancaria) event.getObject();
        addContaBancaria(conta);
        JsfUtil.addSuccessMessage("Conta selecionada!", "Descrição: " + conta.getDescricao());
    }

    public void onContaContabilChosen(SelectEvent event) {
        ContaContabil conta = (ContaContabil) event.getObject();
        addContaContabil(conta);
        JsfUtil.addSuccessMessage("Conta selecionada!", "Descrição: " + conta.getDescricao());
    }
}