/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import tabelas.Banco;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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

        Banco consulta = new Banco();

        List<Order> ordem = new ArrayList<>();
        ordem.add(Order.asc("descricao"));

        lista = consulta.obterLista(null, ordem);
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
        if (codigoBancoNaoCadastrado()) {
            getItem().persiste();
            geraLista();
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"), "");
            RequestContext.getCurrentInstance().execute("$('#myModal').modal('hide')");
        } else {
            JsfUtil.addErrorMessage("Erro de validação de banco.", "Código de banco já cadastrado!");
        }
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

    private boolean codigoBancoNaoCadastrado() {

        Banco consulta = new Banco();
        List<Criterion> filtro = new ArrayList<>();
        filtro.add(Restrictions.and(Restrictions.eq("codigo", getItem().getCodigo()), Restrictions.ne("id", getItem().getIdbanco())));

        return consulta.obterLista(filtro, null).isEmpty();
    }
}
