/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import controls.ContaContabil;
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

@Named("contaContabilCadastro")
@ViewScoped
public class ContaContabilCadastro implements Serializable {

    private ContaContabil contaContabil;
    private DataModel listaDeContasContabeis;
    private boolean gridVisivel;
    private boolean itemVisivel;

    @PostConstruct
    public void abreForm() {
        geraListaDeContasContabeis();
        mostraGrid();
    }

    /**
     * @return the contaContabil
     */
    public ContaContabil getContaContabil() {
        return contaContabil;
    }

    /**
     * @param contaContabil the contaContabil to set
     */
    public void setContaContabil(ContaContabil contaContabil) {
        this.contaContabil = contaContabil;
    }

    /**
     * @return the listaDeContasContabeis
     */
    public DataModel getListaDeContasContabeis() {
        return listaDeContasContabeis;
    }

    /**
     * @param listaDeContaContasbeis the listaDeContasContabeis to set
     */
    public void setListaDeContaContabils(DataModel listaContasContabeis) {
        this.listaDeContasContabeis = listaContasContabeis;
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

    private void geraListaDeContasContabeis() {        
        List<String> ordem = new ArrayList<>();
        ordem.add("descricao");        
        this.listaDeContasContabeis = new CollectionDataModel(ConsultaGeral.consultaTodos( ContaContabil.class, null, null, ordem));
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
        contaContabil = new ContaContabil();
        mostraItem();
    }

    public void edita() {
        setContaContabil((ContaContabil) getListaDeContasContabeis().getRowData());
        mostraItem();
    }

    public void persiste() {
        if (contaContabil.persiste()) {
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"));
            geraListaDeContasContabeis();
            mostraGrid();
        }
    }

    public void exclui() {
        contaContabil = (ContaContabil) getListaDeContasContabeis().getRowData();
        if (contaContabil.exclui()) {
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"));
            geraListaDeContasContabeis();
        }
    }
}