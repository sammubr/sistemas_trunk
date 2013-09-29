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
import persistencia.ConsultaGeral;
import util.JsfUtil;

@Named("contaBancariaCadastro")
@ViewScoped
public class ContaBancariaCadastro implements Serializable {

    private ContaBancaria contaBancaria;
    private DataModel listaDeContasBancarias;
    private boolean gridVisivel;
    private boolean itemVisivel;

    @PostConstruct
    public void abreForm() {
        geraListaDeContasBancarias();
        mostraGrid();
    }

    /**
     * @return the contaBancaria
     */
    public ContaBancaria getContaBancaria() {
        return contaBancaria;
    }

    /**
     * @param contaBancaria the contaBancaria to set
     */
    public void setContaBancaria(ContaBancaria contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    /**
     * @return the listaDeContasBancarias
     */
    public DataModel getListaDeContasBancarias() {
        return listaDeContasBancarias;
    }

    /**
     * @param listaDeContasBancarias the listaDeContasBancarias to set
     */
    public void setListaDeContasBancarias(DataModel listaContas) {
        this.listaDeContasBancarias = listaContas;
    }

    /**
     * @return the gridVisivel
     */
    public boolean isGridVisivel() {
        return gridVisivel;
    }

    /**
     * @param gridVisivel the gridVisivel to set
     */
    public void setGridVisivel(boolean gridVisivel) {
        this.gridVisivel = gridVisivel;
    }

    /**
     * @return the itemVisivel
     */
    public boolean isItemVisivel() {
        return itemVisivel;
    }

    /**
     * @param itemVisivel the itemVisivel to set
     */
    public void setItemVisivel(boolean itemVisivel) {
        this.itemVisivel = itemVisivel;
    }

    private void geraListaDeContasBancarias() {
        List<String> ordem = new ArrayList<>();
        ordem.add("descricao");        
        this.listaDeContasBancarias = new CollectionDataModel(ConsultaGeral.consultaTodos(ContaBancaria.class, null, null, ordem));
    }

    public List getListaDeBancos() {       
        List<String> ordem = new ArrayList<>();
        ordem.add("descricao");        
        return (List) ConsultaGeral.consultaTodos(Banco.class, null, null, ordem);
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
        contaBancaria = new ContaBancaria();
        mostraItem();
    }

    public void edita() {
        setContaBancaria((ContaBancaria) getListaDeContasBancarias().getRowData());
        mostraItem();
    }

    public void persiste() {
        if (contaBancaria.persiste()) {
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"));
            geraListaDeContasBancarias();
            mostraGrid();
        }
    }

    public void exclui() {
        contaBancaria = (ContaBancaria) getListaDeContasBancarias().getRowData();
        if (contaBancaria.exclui()) {
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"));
            geraListaDeContasBancarias();
        }
    }
}