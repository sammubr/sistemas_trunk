/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import tabelas.Banco;
import tabelas.ContaBancaria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.hibernate.criterion.Order;
import org.primefaces.context.RequestContext;
import util.JsfUtil;

@Named("contaBancariaCadastro")
@ViewScoped
public class FormCadastroContaBancaria implements Serializable {

// ------------------------------------------------------------------ ATRIBUTOS
    private ContaBancaria item;
    private List<ContaBancaria> lista;
    private List<ContaBancaria> itensSelecionados;
    private List<Banco> listaDeBancos;

// ---------------------------------------------------------------- CONSTRUCTOR    
    public FormCadastroContaBancaria() {
        geraLista();
    }

    private void geraLista() {
        ContaBancaria consulta = new ContaBancaria();
        List<Order> ordem = new ArrayList<>();        
        ordem.add(Order.asc("descricao"));
        lista = consulta.obterLista(null, ordem);
    }

// --------------------------------------------- GETTERS E SETTERS DESTA CLASSE
    public ContaBancaria getItem() {
        return item;
    }

    public void setItem(ContaBancaria item) {
        this.item = item;
    }

    public List<ContaBancaria> getLista() {
        return lista;
    }

    public void setLista(List<ContaBancaria> lista) {
        this.lista = lista;
    }

    public List<ContaBancaria> getItensSelecionados() {
        return itensSelecionados;
    }

    public void setItensSelecionados(List<ContaBancaria> itensSelecionados) {
        this.itensSelecionados = itensSelecionados;
    }

    public List<Banco> getListaDeBancos() {

        Banco consulta = new Banco();
        List<Order> ordem = new ArrayList<>();        
        ordem.add(Order.asc("descricao"));
        listaDeBancos = consulta.obterLista(null, ordem);

        return listaDeBancos;
    }

    public void setListaDeBancos(List<Banco> listaDeBancos) {
        this.listaDeBancos = listaDeBancos;
    }

// ----------------------------------------------------- MÉTODOS PARA PERSISTIR
    public void criaNovo() {
        item = new ContaBancaria();
    }

    public void edita(ContaBancaria itemSelecionado) {
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
                for (ContaBancaria itemSelecionado : itensSelecionados) {
                    itemSelecionado.exclui();
                }
                geraLista();
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"), "");
                break;
            default:
                for (ContaBancaria itemSelecionado : itensSelecionados) {
                    itemSelecionado.exclui();
                }
                geraLista();
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordsDeleted"), "");
                break;
        }
    }
}
