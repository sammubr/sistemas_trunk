/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import tabelas.ContaContabil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.hibernate.criterion.Order;
import org.primefaces.context.RequestContext;
import util.JsfUtil;

@Named("formCadastroContaContabil")
@ViewScoped
public class FormCadastroContaContabil implements Serializable {

// ------------------------------------------------------------------ ATRIBUTOS
    private ContaContabil item;
    private List<ContaContabil> lista;
    private List<ContaContabil> itensSelecionados;

// ---------------------------------------------------------------- CONSTRUCTOR    
    public FormCadastroContaContabil() {
        geraLista();
    }

    private void geraLista() {
        
        ContaContabil consulta = new ContaContabil();
        
        List<Order> ordem = new ArrayList<>();
        ordem.add(Order.asc("descricao"));       
                
        lista = consulta.obterLista(null, ordem);
    }

// --------------------------------------------- GETTERS E SETTERS DESTA CLASSE
    public ContaContabil getItem() {
        return item;
    }

    public void setItem(ContaContabil item) {
        this.item = item;
    }

    public List<ContaContabil> getLista() {
        return lista;
    }

    public void setLista(List<ContaContabil> lista) {
        this.lista = lista;
    }

    public List<ContaContabil> getItensSelecionados() {
        return itensSelecionados;
    }

    public void setItensSelecionados(List<ContaContabil> itensSelecionados) {
        this.itensSelecionados = itensSelecionados;
    }

// ----------------------------------------------------- MÉTODOS PARA PERSISTIR
    public void criaNovo() {
        item = new ContaContabil();
    }

    public void edita(ContaContabil itemSelecionado) {
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
                for (ContaContabil itemSelecionado : itensSelecionados) {
                    itemSelecionado.exclui();
                }
                geraLista();
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"), "");
                break;
            default:
                for (ContaContabil itemSelecionado : itensSelecionados) {
                    itemSelecionado.exclui();
                }
                geraLista();
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordsDeleted"), "");
                break;
        }
    }
}
