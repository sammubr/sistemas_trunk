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
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import util.JsfUtil;

@Named("usuarioItem")
@SessionScoped
public class UsuarioItem implements Serializable {

// ------------------------------------------------------------------ ATRIBUTOS
    private Usuario item;

    // ---------------------------------------------------------------- CONSTRUCTOR

// --------------------------------------------- GETTERS E SETTERS DESTA CLASSE
    public Usuario getItem() {
        return item;
    }

    public void setItem(Usuario item) {
        this.item = item;
    }
// ----------------------------------------------------- MÉTODOS PARA PERSISTIR

    public void criaNovo() {
        setItem(new Usuario());
        RequestContext.getCurrentInstance().openDialog("item.xhtml");
    }

    public void persiste() {
        getItem().persiste();
        RequestContext.getCurrentInstance().closeDialog(this);
    }

    public String edita(Usuario item) {
        setItem(item);
        return "cadastroItem.xhtml";
    }
}