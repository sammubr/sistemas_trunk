/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import controls.ContaBancaria;
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
import util.JsfUtil;

@Named("relacionamentoCadastro")
@ViewScoped
public class RelacionamentoCadastro implements Serializable {

    private RelContabilidadeBanco fRelacionamento;
    private DataModel fListaDeRelacionamentos;
    private DataModel fListaDeContasBancarias;
    private DataModel fListaDeContasContabeis;
    private boolean fGridVisivel;
    private boolean fItemVisivel;

    @PostConstruct
    public void abreForm() {
        geraListaDeRelacionamentos();
        mostraGrid();
    }

    /**
     * @return the banco
     */
    public RelContabilidadeBanco getRelacionamento() {
        return fRelacionamento;
    }

    /**
     * @param banco the banco to set
     */
    public void setRelacionamento(RelContabilidadeBanco relacionamento) {
        this.fRelacionamento = relacionamento;
    }

    /**
     * @return the listaDeBancos
     */
    public DataModel getListaDeRelacionamentos() {
        return fListaDeRelacionamentos;
    }

    /**
     * @param listaDeBancos the listaDeBancos to set
     */
    public void setListaDeRelacionamentos(DataModel listaRelacionamentos) {
        this.fListaDeRelacionamentos = listaRelacionamentos;
    }

    /**
     * @return the gridVisivel
     */
    public boolean isGridVisivel() {
        return fGridVisivel;
    }

    /**
     * @param gridVisivel the gridVisivel to set
     */
    public void setGridVisivel(boolean gridVisivel) {
        this.fGridVisivel = gridVisivel;
    }

    /**
     * @return the itemVisivel
     */
    public boolean isItemVisivel() {
        return fItemVisivel;
    }

    /**
     * @param itemVisivel the itemVisivel to set
     */
    public void setItemVisivel(boolean itemVisivel) {
        this.fItemVisivel = itemVisivel;
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
        //geraListaDeContasBancarias();
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

    public DataModel getListaDeContasBancarias() {
        return fListaDeContasBancarias;
    }

    public DataModel getListaDeContasContabeis() {
        return fListaDeContasContabeis;
    }

    public void setListaDeContasContabeis(DataModel listaDeContasContabeis) {
        this.fListaDeContasContabeis = listaDeContasContabeis;
    }

    /**
     * @param listaDeContasBancarias the listaDeContasBancarias to set
     */
    public void setListaDeContasBancarias(DataModel listaDeContasBancarias) {
        this.fListaDeContasBancarias = listaDeContasBancarias;
    }

    private void geraListaDeContasBancarias() {

//        List<String> atributos = new ArrayList<>();
//        atributos.add("relContabilidadeBanco");

//        List<Object> valores = new ArrayList<>();
//        valores.add(fRelacionamento);

        List<String> ordem = new ArrayList<>();
        ordem.add("descricao");

        ContaBancaria contaBancaria = new ContaBancaria();
        this.fListaDeContasBancarias = new CollectionDataModel(contaBancaria.obter(null, null, ordem));

    }

    public void addContaBancaria() {
    }

    public void removeContaBancaria() {
    }

    public void addContaContabil() {
    }

    public void removeContaContabil() {
    }
}