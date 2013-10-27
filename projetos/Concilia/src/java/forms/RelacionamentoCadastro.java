/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import controls.ContaContabil;
import controls.RelContabilidadeBanco;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import util.JsfUtil;

@Named("relacionamentoCadastro")
@SessionScoped
public class RelacionamentoCadastro implements Serializable {

// ------------------------------------------------------------------ ATRIBUTOS
    private RelContabilidadeBanco item;
    private List<RelContabilidadeBanco> lista;
    private List<RelContabilidadeBanco> itensSelecionados;
    private List<ContaContabil> listaContasContabeis;
    private List<ContaContabil> contasContabeisSelecionadas;

// ---------------------------------------------------------------- CONSTRUCTOR    
    public RelacionamentoCadastro() {
        geraLista();
    }

    private void geraLista() {
        List<String> ordem = new ArrayList<>();
        ordem.add("descricao");
        RelContabilidadeBanco consulta = new RelContabilidadeBanco();
        lista = (List) consulta.obter(null, null, ordem);
    }

// --------------------------------------------- GETTERS E SETTERS DESTA CLASSE
    public RelContabilidadeBanco getItem() {
        return item;
    }

    public void setItem(RelContabilidadeBanco item) {
        this.item = item;
    }

    public List<RelContabilidadeBanco> getLista() {
        return lista;
    }

    public void setLista(List<RelContabilidadeBanco> lista) {
        this.lista = lista;
    }

    public List<RelContabilidadeBanco> getItensSelecionados() {
        return itensSelecionados;
    }

    public void setItensSelecionados(List<RelContabilidadeBanco> itensSelecionados) {
        this.itensSelecionados = itensSelecionados;
    }

    public List<ContaContabil> getListaContasContabeis() {





        return listaContasContabeis;
    }

    public void setListaContasContabeis(List<ContaContabil> listaContasContabeis) {
        this.listaContasContabeis = listaContasContabeis;
    }

    public List<ContaContabil> getContasContabeisSelecionadas() {
        return contasContabeisSelecionadas;
    }

    public void setContasContabeisSelecionadas(List<ContaContabil> contasContabeisSelecionadas) {
        this.contasContabeisSelecionadas = contasContabeisSelecionadas;
    }

// ----------------------------------------------------- MÉTODOS PARA PERSISTIR
    public String criaNovo() {
        setItem(new RelContabilidadeBanco());
        return "cadastroItem.xhtml";
    }

    public String persiste() {
        getItem().persiste();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"), "");
        geraLista();
        return "cadastroList.xhtml";
    }

    public String edita(RelContabilidadeBanco item) {
        setItem(item);
        return "cadastroItem.xhtml";
    }

    public void exclui() {
        switch (itensSelecionados.size()) {
            case 0:
                JsfUtil.addErrorMessage("Não há registros para excluir!", "");
                break;
            case 1:
                for (RelContabilidadeBanco itemSelecionado : itensSelecionados) {
                    itemSelecionado.exclui();
                }
                geraLista();
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"), "");
                break;
            default:
                for (RelContabilidadeBanco itemSelecionado : itensSelecionados) {
                    itemSelecionado.exclui();
                }
                geraLista();
                JsfUtil.addSuccessMessage("Registros excluídos com sucesso!", "");
                break;
        }
    }

    public void onContaContabilChosen(SelectEvent event) {
        ContaContabil conta = (ContaContabil) event.getObject();
        addContaContabil(conta);
    }

    public void addContaContabil(ContaContabil conta) {
        if (listaContasContabeis.contains(conta)) {
            JsfUtil.addErrorMessage("Erro!", "A conta já está selecionada!");
        } else {
            listaContasContabeis.add(conta);
            JsfUtil.addSuccessMessage("Conta selecionada!", "Descrição: " + conta.getDescricao());
        }
    }

    public List contasContabeisSemRelacionamento() {

        List<String> atributos = new ArrayList<>();
        atributos.add("relContabilidadeBanco");

        List<Object> valores = new ArrayList<>();
        valores.add(null);

        List<String> ordem = new ArrayList<>();
        ordem.add("descricao");

        ContaContabil conta = new ContaContabil();
        return (List) conta.obter(atributos, valores, ordem);
    }
}
