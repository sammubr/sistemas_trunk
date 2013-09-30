/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import controls.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.model.CollectionDataModel;
import javax.faces.model.DataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.Table;
import util.JsfUtil;

@Named("usuarioCadastro")
@ViewScoped
public class UsuarioCadastro implements Serializable {

    private Usuario fUsuario;
    private DataModel fListaDeUsuarios;
    private boolean fGridVisivel;
    private boolean fItemVisivel;

    @PostConstruct
    public void abreForm() {
        geraListaDeUsuarios();
        mostraGrid();
    }

    /**
     * @return the fUsuario
     */
    public Usuario getUsuario() {
        return fUsuario;
    }

    /**
     * @param fUsuario the fUsuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.fUsuario = usuario;
    }

    /**
     * @return the listaDeUsuarios
     */
    public DataModel getListaDeUsuarios() {
        return fListaDeUsuarios;
    }

    /**
     * @param listaDeUsuarios the listaDeUsuarios to set
     */
    public void setListaDeUsuarios(DataModel listaUsuarios) {
        this.fListaDeUsuarios = listaUsuarios;
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

    private void geraListaDeUsuarios() {
        List<String> ordem = new ArrayList<>();
        ordem.add("nome");
        Usuario usuario = new Usuario();
        this.fListaDeUsuarios = new CollectionDataModel(usuario.obter(null, null, ordem));        
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
        fUsuario = new Usuario();
        mostraItem();
    }

    public void edita() {
        setUsuario((Usuario) getListaDeUsuarios().getRowData());
        mostraItem();
    }

    public void persiste() {
        if (fUsuario.persiste()) {
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"));
            geraListaDeUsuarios();
            mostraGrid();
        }
    }

    public void exclui() {
        fUsuario = (Usuario) getListaDeUsuarios().getRowData();
        if (fUsuario.exclui()) {
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"));
            geraListaDeUsuarios();
        }
    }
}