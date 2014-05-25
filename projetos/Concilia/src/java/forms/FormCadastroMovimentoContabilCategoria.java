package forms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.hibernate.criterion.Order;
import org.primefaces.context.RequestContext;
import tabelas.ContabilidadeCategoria;
import util.JsfUtil;

@Named("formCadastroMovimentoContabilCategoria")
@ViewScoped
public class FormCadastroMovimentoContabilCategoria implements Serializable {

// ------------------------------------------------------------------ ATRIBUTOS
    private ContabilidadeCategoria item;
    private List<ContabilidadeCategoria> lista;
    private List<ContabilidadeCategoria> itensSelecionados;

// ---------------------------------------------------------------- CONSTRUCTOR    
    public FormCadastroMovimentoContabilCategoria() {
        geraLista();
    }

    private void geraLista() {

        ContabilidadeCategoria consulta = new ContabilidadeCategoria();

        List<Order> ordem = new ArrayList<>();
        ordem.add(Order.asc("descricao"));

        lista = consulta.obterLista(null, ordem);
    }

// --------------------------------------------- GETTERS E SETTERS DESTA CLASSE
    public ContabilidadeCategoria getItem() {
        return item;
    }

    public void setItem(ContabilidadeCategoria item) {
        this.item = item;
    }

    public List<ContabilidadeCategoria> getLista() {
        return lista;
    }

    public void setLista(List<ContabilidadeCategoria> lista) {
        this.lista = lista;
    }

    public List<ContabilidadeCategoria> getItensSelecionados() {
        return itensSelecionados;
    }

    public void setItensSelecionados(List<ContabilidadeCategoria> itensSelecionados) {
        this.itensSelecionados = itensSelecionados;
    }

// ----------------------------------------------------- MÉTODOS PARA PERSISTIR
    public void criaNovo() {
        item = new ContabilidadeCategoria();
    }

    public void edita(ContabilidadeCategoria itemSelecionado) {
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
                for (ContabilidadeCategoria itemSelecionado : itensSelecionados) {
                    if (itemSelecionado.getContaContabilMovimentoCollection().isEmpty()) {
                        itemSelecionado.exclui();
                        geraLista();
                        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"), "");
                    } else {
                        JsfUtil.addErrorMessage("Impossível excluir", "Existem relacionamentos com o registro selecionado!");
                    }
                }
                break;
            default:
                for (ContabilidadeCategoria itemSelecionado : itensSelecionados) {
                    if (itemSelecionado.getContaContabilMovimentoCollection().isEmpty()) {
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
