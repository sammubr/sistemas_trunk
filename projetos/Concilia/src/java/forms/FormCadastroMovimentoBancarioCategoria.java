package forms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.hibernate.criterion.Order;
import org.primefaces.context.RequestContext;
import tabelas.BancoCategoria;
import util.JsfUtil;

@Named("formCadastroMovimentoBancarioCategoria")
@ViewScoped
public class FormCadastroMovimentoBancarioCategoria implements Serializable {

// ------------------------------------------------------------------ ATRIBUTOS
    private BancoCategoria item;
    private List<BancoCategoria> lista;
    private List<BancoCategoria> itensSelecionados;

// ---------------------------------------------------------------- CONSTRUCTOR    
    public FormCadastroMovimentoBancarioCategoria() {
        geraLista();
    }

    private void geraLista() {

        BancoCategoria consulta = new BancoCategoria();

        List<Order> ordem = new ArrayList<>();
        ordem.add(Order.asc("descricao"));

        lista = consulta.obterLista(null, ordem);
    }

// --------------------------------------------- GETTERS E SETTERS DESTA CLASSE
    public BancoCategoria getItem() {
        return item;
    }

    public void setItem(BancoCategoria item) {
        this.item = item;
    }

    public List<BancoCategoria> getLista() {
        return lista;
    }

    public void setLista(List<BancoCategoria> lista) {
        this.lista = lista;
    }

    public List<BancoCategoria> getItensSelecionados() {
        return itensSelecionados;
    }

    public void setItensSelecionados(List<BancoCategoria> itensSelecionados) {
        this.itensSelecionados = itensSelecionados;
    }

// ----------------------------------------------------- MÉTODOS PARA PERSISTIR
    public void criaNovo() {
        item = new BancoCategoria();
    }

    public void edita(BancoCategoria itemSelecionado) {
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
                for (BancoCategoria itemSelecionado : itensSelecionados) {
                    if (itemSelecionado.getContaBancariaMovimentoCollection().isEmpty()) {
                        itemSelecionado.exclui();
                        geraLista();
                        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"), "");
                    } else {
                        JsfUtil.addErrorMessage("Impossível excluir", "Existem relacionamentos com o registro selecionado!");
                    }
                }
                break;
            default:
                for (BancoCategoria itemSelecionado : itensSelecionados) {
                    if (itemSelecionado.getContaBancariaMovimentoCollection().isEmpty()) {
                        itemSelecionado.exclui();
                        geraLista();
                        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordsDeleted"), "");
                    } else {
                        JsfUtil.addErrorMessage("Impossível excluir", "Existem relacionamentos com o registro selecionado!");
                    }
                }
                break;
        }
    }

}
