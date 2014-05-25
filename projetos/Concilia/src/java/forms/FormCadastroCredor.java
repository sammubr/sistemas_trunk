package forms;

import tabelas.Credor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.hibernate.criterion.Order;
import org.primefaces.context.RequestContext;
import util.JsfUtil;

@Named("formCadastroCredor")
@ViewScoped
public class FormCadastroCredor implements Serializable {

// ------------------------------------------------------------------ ATRIBUTOS
    private Credor item;
    private List<Credor> lista;
    private List<Credor> itensSelecionados;

// ---------------------------------------------------------------- CONSTRUCTOR    
    public FormCadastroCredor() {
        geraLista();
    }

    private void geraLista() {
        Credor consulta = new Credor();

        List<Order> ordem = new ArrayList<>();
        ordem.add(Order.asc("razaoSocialNome"));

        lista = consulta.obterLista(null, ordem);
    }

// --------------------------------------------- GETTERS E SETTERS DESTA CLASSE
    public Credor getItem() {
        return item;
    }

    public void setItem(Credor item) {
        this.item = item;
    }

    public List<Credor> getLista() {
        return lista;
    }

    public void setLista(List<Credor> lista) {
        this.lista = lista;
    }

    public List<Credor> getItensSelecionados() {
        return itensSelecionados;
    }

    public void setItensSelecionados(List<Credor> itensSelecionados) {
        this.itensSelecionados = itensSelecionados;
    }

// ----------------------------------------------------- MÉTODOS PARA PERSISTIR
    public void criaNovo() {
        item = new Credor();
    }

    public void edita(Credor itemSelecionado) {
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
                for (Credor itemSelecionado : itensSelecionados) {
                    if (itemSelecionado.getContaBancariaMovimentoCollection().isEmpty() && itemSelecionado.getContaContabilMovimentoCollection().isEmpty()) {
                        itemSelecionado.exclui();
                        geraLista();
                        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"), "");
                    } else {
                        JsfUtil.addErrorMessage("Impossível excluir", "Existem relacionamentos com o registro selecionado!");
                    }
                }
                break;
            default:
                for (Credor itemSelecionado : itensSelecionados) {
                    if (itemSelecionado.getContaBancariaMovimentoCollection().isEmpty() && itemSelecionado.getContaContabilMovimentoCollection().isEmpty()) {
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
