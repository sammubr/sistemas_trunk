/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import controls.Banco;
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

@Named("bancoCadastro")
@ViewScoped
public class BancoCadastro implements Serializable {

    private Banco fBanco;
    private DataModel fListaDeBancos;
    private boolean fGridVisivel;
    private boolean fItemVisivel;

    @PostConstruct
    public void abreForm() {
        geraListaDeBancos();
        mostraGrid();
    }

    /**
     * @return the banco
     */
    public Banco getBanco() {
        return fBanco;
    }

    /**
     * @param banco the banco to set
     */
    public void setBanco(Banco banco) {
        this.fBanco = banco;
    }

    /**
     * @return the listaDeBancos
     */
    public DataModel getListaDeBancos() {
        return fListaDeBancos;
    }

    /**
     * @param listaDeBancos the listaDeBancos to set
     */
    public void setListaDeBancos(DataModel listaBancos) {
        this.fListaDeBancos = listaBancos;
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

    private void geraListaDeBancos() {
        List<String> ordem = new ArrayList<>();
        ordem.add("descricao");
        Banco banco = new Banco();
        this.fListaDeBancos = new CollectionDataModel(banco.obter(null, null, ordem));
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
        fBanco = new Banco();
        mostraItem();
    }

    public void edita() {
        setBanco((Banco) getListaDeBancos().getRowData());
        mostraItem();
    }

    public void persiste() {
        fBanco.persiste();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"));
        geraListaDeBancos();
        mostraGrid();
    }

    public void exclui() {
        fBanco = (Banco) getListaDeBancos().getRowData();
        fBanco.exclui();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"));
        geraListaDeBancos();

    }
}