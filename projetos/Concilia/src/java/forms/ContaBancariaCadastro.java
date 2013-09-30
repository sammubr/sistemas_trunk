/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import controls.Banco;
import controls.ContaBancaria;
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

@Named("contaBancariaCadastro")
@ViewScoped
public class ContaBancariaCadastro implements Serializable {

    private ContaBancaria fContaBancaria;
    private DataModel fListaDeContasBancarias;
    private boolean fGridVisivel;
    private boolean fItemVisivel;

    @PostConstruct
    public void abreForm() {
        geraListaDeContasBancarias();
        mostraGrid();
    }

    /**
     * @return the contaBancaria
     */
    public ContaBancaria getContaBancaria() {
        return fContaBancaria;
    }

    /**
     * @param contaBancaria the contaBancaria to set
     */
    public void setContaBancaria(ContaBancaria contaBancaria) {
        this.fContaBancaria = contaBancaria;
    }

    /**
     * @return the listaDeContasBancarias
     */
    public DataModel getListaDeContasBancarias() {
        return fListaDeContasBancarias;
    }

    /**
     * @param listaDeContasBancarias the listaDeContasBancarias to set
     */
    public void setListaDeContasBancarias(DataModel listaContas) {
        this.fListaDeContasBancarias = listaContas;
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

    private void geraListaDeContasBancarias() {
        List<String> ordem = new ArrayList<>();
        ordem.add("descricao");        
        ContaBancaria contaBancaria = new ContaBancaria();
        this.fListaDeContasBancarias = new CollectionDataModel(contaBancaria.obter(null, null, ordem));
    }

    public List getListaDeBancos() {       
        List<String> ordem = new ArrayList<>();
        ordem.add("descricao");
        Banco banco = new Banco();
        return (List) banco.obter(null, null, ordem);
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
        fContaBancaria = new ContaBancaria();
        mostraItem();
    }

    public void edita() {
        setContaBancaria((ContaBancaria) getListaDeContasBancarias().getRowData());
        mostraItem();
    }

    public void persiste() {
        if (fContaBancaria.persiste()) {
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"));
            geraListaDeContasBancarias();
            mostraGrid();
        }
    }

    public void exclui() {
        fContaBancaria = (ContaBancaria) getListaDeContasBancarias().getRowData();
        if (fContaBancaria.exclui()) {
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"));
            geraListaDeContasBancarias();
        }
    }
}