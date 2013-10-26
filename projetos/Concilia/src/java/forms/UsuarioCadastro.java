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
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import util.JsfUtil;

/**
 *
 * @author samuel
 */
@Named("usuarioCadastro")
@SessionScoped
public class UsuarioCadastro implements Serializable {

// ------------------------------------------------------------------ ATRIBUTOS
    private Usuario item;
    private List<Usuario> lista;
    private List<Usuario> itensSelecionados;

// ---------------------------------------------------------------- CONSTRUCTOR    
    public UsuarioCadastro() {
        geraLista();
    }

    private void geraLista() {
        List<String> ordem = new ArrayList<>();
        ordem.add("nome");
        Usuario consulta = new Usuario();
        lista = (List) consulta.obter(null, null, ordem);
    }

// --------------------------------------------- GETTERS E SETTERS DESTA CLASSE
    public Usuario getItem() {
        return item;
    }

    public void setItem(Usuario item) {
        this.item = item;
    }

    public List<Usuario> getLista() {
        return lista;
    }

    public void setLista(List<Usuario> lista) {
        this.lista = lista;
    }

    public List<Usuario> getItensSelecionados() {
        return itensSelecionados;
    }

    public void setItensSelecionados(List<Usuario> itensSelecionados) {
        this.itensSelecionados = itensSelecionados;
    }

// ----------------------------------------------------- MÉTODOS PARA PERSISTIR
    public String criaNovo() {
        setItem(new Usuario());
        return "cadastroItem.xhtml";
    }

    public String persiste() {
        getItem().persiste();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"), "");
        return "cadastroList.xhtml";
    }

    public String edita(Usuario item) {
        setItem(item);
        return "cadastroItem.xhtml";
    }

    public void exclui() {
        switch (itensSelecionados.size()) {
            case 0:
                JsfUtil.addErrorMessage("Não há registros para excluir!", "");
                break;
            case 1:
                for (Usuario itemSelecionado : itensSelecionados) {
                    itemSelecionado.exclui();
                }
                geraLista();
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"), "");
                break;
            default:
                for (Usuario itemSelecionado : itensSelecionados) {
                    itemSelecionado.exclui();
                }
                geraLista();
                JsfUtil.addSuccessMessage("Registros excluídos com sucesso!", "");
                break;
        }
    }
}
