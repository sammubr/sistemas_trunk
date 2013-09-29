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
import persistencia.ConsultaGeral;
import util.JsfUtil;

@Named("bancoCadastro")
@ViewScoped
public class BancoCadastro implements Serializable {

    private Banco banco;
    private DataModel listaDeBancos;
    private boolean gridVisivel;
    private boolean itemVisivel;

    @PostConstruct
    public void abreForm() {
        geraListaDeBancos();
        mostraGrid();
    }

    /**
     * @return the banco
     */
    public Banco getBanco() {
        return banco;
    }

    /**
     * @param banco the banco to set
     */
    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    /**
     * @return the listaDeBancos
     */
    public DataModel getListaDeBancos() {
        return listaDeBancos;
    }

    /**
     * @param listaDeBancos the listaDeBancos to set
     */
    public void setListaDeBancos(DataModel listaBancos) {
        this.listaDeBancos = listaBancos;
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

    private void geraListaDeBancos() {
        List<String> ordem = new ArrayList<>();
        ordem.add("descricao");        
        this.listaDeBancos = new CollectionDataModel(ConsultaGeral.consultaTodos(Banco.class, null, null, ordem));
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
        banco = new Banco();
        mostraItem();
    }

    public void edita() {
        setBanco((Banco) getListaDeBancos().getRowData());
        mostraItem();
    }

    public void persiste() {
        if (banco.persiste()) {
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"));
            geraListaDeBancos();
            mostraGrid();
        }
    }

    public void exclui() {
        banco = (Banco) getListaDeBancos().getRowData();
        if (banco.exclui()) {
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"));
            geraListaDeBancos();
        }
    }
}