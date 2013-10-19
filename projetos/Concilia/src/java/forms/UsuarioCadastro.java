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
import util.JsfUtil;

@Named("usuarioCadastro")
@ViewScoped
public class UsuarioCadastro implements Serializable {

    private Usuario fUsuario;
    private DataModel fListaDeUsuarios;

    @PostConstruct
    public void abreForm() {
        geraListaDeUsuarios();
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

    private void geraListaDeUsuarios() {
        List<String> ordem = new ArrayList<>();
        ordem.add("nome");
        Usuario usuario = new Usuario();
        this.fListaDeUsuarios = new CollectionDataModel(usuario.obter(null, null, ordem));
    }

    public void criaNovo() {
        fUsuario = new Usuario();
    }

    public void edita() {
        setUsuario((Usuario) getListaDeUsuarios().getRowData());
    }

    public void persiste() {
        fUsuario.persiste();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"), "");
        geraListaDeUsuarios();

    }

    public void exclui() {
        fUsuario = (Usuario) getListaDeUsuarios().getRowData();
        fUsuario.exclui();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"), "");
        geraListaDeUsuarios();

    }
}