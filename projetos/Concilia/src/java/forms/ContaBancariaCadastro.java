/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import controls.Banco;
import controls.ContaBancaria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import util.JsfUtil;

@Named("contaBancariaCadastro")
@SessionScoped
public class ContaBancariaCadastro implements Serializable {

// ------------------------------------------------------------------ ATRIBUTOS
    private ContaBancaria item;
    private List<ContaBancaria> lista;
    private List<ContaBancaria> itensSelecionados;
    private List<Banco> listaDeBancos;

// ---------------------------------------------------------------- CONSTRUCTOR    
    public ContaBancariaCadastro() {
        geraLista();
    }

    private void geraLista() {
        List<String> ordem = new ArrayList<>();
        ordem.add("descricao");
        ContaBancaria consulta = new ContaBancaria();
        lista = (List) consulta.obter(null, null, ordem);
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

        List<String> ordem = new ArrayList<>();
        ordem.add("descricao");
        Banco consulta = new Banco();
        listaDeBancos = (List) consulta.obter(null, null, ordem);

        return listaDeBancos;
    }

    public void setListaDeBancos(List<Banco> listaDeBancos) {
        this.listaDeBancos = listaDeBancos;
    }

// ----------------------------------------------------- MÉTODOS PARA PERSISTIR
    public String criaNovo() {
        setItem(new ContaBancaria());
        return "cadastroItem.xhtml";
    }

    public String persiste() {
        getItem().persiste();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"), "");
        geraLista();
        return "cadastroList.xhtml";
    }

    public String edita(ContaBancaria item) {
        setItem(item);
        return "cadastroItem.xhtml";
    }

    public void exclui() {
        switch (itensSelecionados.size()) {
            case 0:
                JsfUtil.addErrorMessage("Não há registros para excluir!", "");
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
                JsfUtil.addSuccessMessage("Registros excluídos com sucesso!", "");
                break;
        }
    }
}
