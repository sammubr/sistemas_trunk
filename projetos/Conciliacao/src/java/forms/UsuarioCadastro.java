/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import controls.Usuario;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.model.CollectionDataModel;
import javax.faces.model.DataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import persistencia.ConsultaGeral;
import util.JsfUtil;

@Named("usuarioCadastro")
@ViewScoped
public class UsuarioCadastro implements Serializable {

    private Usuario usuario;
    private DataModel listaDeUsuarios;
    private boolean gridVisivel;
    private boolean itemVisivel;

    @PostConstruct
    public void abreForm() {
        geraListaDeUsuarios();
        mostraGrid();
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the listaDeUsuarios
     */
    public DataModel getListaDeUsuarios() {
        return listaDeUsuarios;
    }

    /**
     * @param listaDeUsuarios the listaDeUsuarios to set
     */
    public void setListaDeUsuarios(DataModel listaUsuarios) {
        this.listaDeUsuarios = listaUsuarios;
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

    private void geraListaDeUsuarios() {
        this.listaDeUsuarios = new CollectionDataModel(ConsultaGeral.consultaTodos(Usuario.class, "nome"));
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
        usuario = new Usuario();
        mostraItem();
    }

    public void edita() {
        setUsuario((Usuario) getListaDeUsuarios().getRowData());
        mostraItem();
    }

    public void persiste() {
        if (usuario.persiste()) {
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"));
            geraListaDeUsuarios();
            mostraGrid();
        }
    }

    public void exclui() {
        usuario = (Usuario) getListaDeUsuarios().getRowData();
        if (usuario.exclui()) {
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"));
            geraListaDeUsuarios();
        }
    }
}