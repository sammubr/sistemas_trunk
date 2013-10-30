/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import controls.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import util.JsfUtil;

@Named("usuarioCadastro")
@RequestScoped
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
    public void criaNovo() {
        setItem(new Usuario());
        abreCadastro();
    }

    public void persiste() {
        //getItem().persiste();
        RequestContext.getCurrentInstance().closeDialog(this);
    }

    public void edita(Usuario item) {
        setItem(item);
        abreCadastro();
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

    public void aoSalvar(SelectEvent event) {
        //     Usuario usuario = (Usuario) event.getObject();  
        geraLista();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"), "");
    }

    private void abreCadastro() {

        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);

        RequestContext.getCurrentInstance().openDialog("item.xhtml", options, null);
    }
}
