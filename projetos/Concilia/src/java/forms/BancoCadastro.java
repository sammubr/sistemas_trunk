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
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import util.JsfUtil;

@Named("bancoCadastro")
@ViewScoped
public class BancoCadastro implements Serializable {

// ------------------------------------------------------------------ ATRIBUTOS
    private Banco item;
    private List<Banco> lista;
    private List<Banco> itensSelecionados;

// ---------------------------------------------------------------- CONSTRUCTOR    
    public BancoCadastro() {
        geraLista();
    }

    private void geraLista() {
        List<String> ordem = new ArrayList<>();
        ordem.add("descricao");
        Banco consulta = new Banco();
        lista = (List) consulta.obter(null, null, ordem);
    }

// --------------------------------------------- GETTERS E SETTERS DESTA CLASSE
    public Banco getItem() {
        return item;
    }

    public void setItem(Banco item) {
        this.item = item;
    }

    public List<Banco> getLista() {
        return lista;
    }

    public void setLista(List<Banco> lista) {
        this.lista = lista;
    }

    public List<Banco> getItensSelecionados() {
        return itensSelecionados;
    }

    public void setItensSelecionados(List<Banco> itensSelecionados) {
        this.itensSelecionados = itensSelecionados;
    }

// ----------------------------------------------------- MÉTODOS PARA PERSISTIR
    public void criaNovo() {
        item = new Banco();
    }

    public void edita(Banco itemSelecionado) {
        item = itemSelecionado;
    }

    public void persiste() {
        getItem().persiste();
        geraLista();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"), "");
        RequestContext.getCurrentInstance().execute("$('#myModal').modal('hide')");
    }

    public void exclui() {
        switch (itensSelecionados.size()) {
            case 0:
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("EmptyRecordsToDelete"), "");
                break;
            case 1:
                for (Banco itemSelecionado : itensSelecionados) {
                    itemSelecionado.exclui();
                }
                geraLista();
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"), "");
                break;
            default:
                for (Banco itemSelecionado : itensSelecionados) {
                    itemSelecionado.exclui();
                }
                geraLista();
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordsDeleted"), "");
                break;
        }
    }
}
