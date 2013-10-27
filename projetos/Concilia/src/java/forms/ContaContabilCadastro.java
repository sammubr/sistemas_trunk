/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import controls.Banco;
import controls.ContaContabil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import util.JsfUtil;

@Named("contaContabilCadastro")
@SessionScoped
public class ContaContabilCadastro implements Serializable {

// ------------------------------------------------------------------ ATRIBUTOS
    private ContaContabil item;
    private List<ContaContabil> lista;
    private List<ContaContabil> itensSelecionados;

// ---------------------------------------------------------------- CONSTRUCTOR    
    public ContaContabilCadastro() {
        geraLista();
    }

    private void geraLista() {
        List<String> ordem = new ArrayList<>();
        ordem.add("descricao");
        ContaContabil consulta = new ContaContabil();
        lista = (List) consulta.obter(null, null, ordem);
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
    public String criaNovo() {
        setItem(new ContaContabil());
        return "cadastroItem.xhtml";
    }

    public String persiste() {
        getItem().persiste();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"), "");
        geraLista();
        return "cadastroList.xhtml";
    }

    public String edita(ContaContabil item) {
        setItem(item);
        return "cadastroItem.xhtml";
    }

    public void exclui() {
        switch (itensSelecionados.size()) {
            case 0:
                JsfUtil.addErrorMessage("Não há registros para excluir!", "");
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
                JsfUtil.addSuccessMessage("Registros excluídos com sucesso!", "");
                break;
        }
    }
}
