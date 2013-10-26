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
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import util.JsfUtil;

@Named("bancoCadastro")
@SessionScoped
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
    public String criaNovo() {
        setItem(new Banco());
        return "cadastroItem.xhtml";
    }

    public String persiste() {
        getItem().persiste();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"), "");
        geraLista();
        return "cadastroList.xhtml";
    }

    public String edita(Banco item) {
        setItem(item);
        return "cadastroItem.xhtml";
    }

    public void exclui() {
        switch (itensSelecionados.size()) {
            case 0:
                JsfUtil.addErrorMessage("Não há registros para excluir!", "");
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
                JsfUtil.addSuccessMessage("Registros excluídos com sucesso!", "");
                break;
        }
    }
}
