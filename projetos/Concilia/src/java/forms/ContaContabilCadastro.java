/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import controls.ContaContabil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
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
    public void criaNovo() {
        setItem(new ContaContabil());
        abreCadastro();
    }

    public void edita(ContaContabil item) {
        setItem(item);
        abreCadastro();
    }

    public void persiste() {
        getItem().persiste();
        RequestContext.getCurrentInstance().closeDialog(getItem());
    }

    public void cancela() {
        RequestContext.getCurrentInstance().closeDialog(null);
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

    public void aoSalvar(SelectEvent event) {
        ContaContabil novoItem = (ContaContabil) event.getObject();
        if (novoItem != null) {
            geraLista();
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"), "");
        }
    }

// ------------------------------------------------------------------- DIVERSOS
    private void abreCadastro() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("resizable", false);
        RequestContext.getCurrentInstance().openDialog("item.xhtml", options, null);
    }
}
