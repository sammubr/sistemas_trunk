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
import util.JsfUtil;

@Named("contaContabilCadastro")
@ViewScoped
public class ContaContabilCadastro implements Serializable {

    private ContaContabil fContaContabil;
    private DataModel fListaDeContasContabeis;
    private boolean fGridVisivel;
    private boolean fItemVisivel;

    @PostConstruct
    public void abreForm() {
        geraListaDeContasContabeis();
        mostraGrid();
    }

    /**
     * @return the contaContabil
     */
    public ContaContabil getContaContabil() {
        return fContaContabil;
    }

    /**
     * @param contaContabil the contaContabil to set
     */
    public void setContaContabil(ContaContabil contaContabil) {
        this.fContaContabil = contaContabil;
    }

    /**
     * @return the listaDeContasContabeis
     */
    public DataModel getListaDeContasContabeis() {
        return fListaDeContasContabeis;
    }

    /**
     * @param listaDeContaContasbeis the listaDeContasContabeis to set
     */
    public void setListaDeContaContabils(DataModel listaContasContabeis) {
        this.fListaDeContasContabeis = listaContasContabeis;
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

    private void geraListaDeContasContabeis() {
        List<String> ordem = new ArrayList<>();
        ordem.add("descricao");
        ContaContabil contaContabil = new ContaContabil();
        this.fListaDeContasContabeis = new CollectionDataModel(contaContabil.obter(null, null, ordem));
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
        fContaContabil = new ContaContabil();
        mostraItem();
    }

    public void edita() {
        setContaContabil((ContaContabil) getListaDeContasContabeis().getRowData());
        mostraItem();
    }

    public void persiste() {
        fContaContabil.persiste();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"),"");
        geraListaDeContasContabeis();
        mostraGrid();

    }

    public void exclui() {
        fContaContabil = (ContaContabil) getListaDeContasContabeis().getRowData();
        fContaContabil.exclui();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"),"");
        geraListaDeContasContabeis();

    }
}