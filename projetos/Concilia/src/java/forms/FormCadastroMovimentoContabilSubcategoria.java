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
import tabelas.ContabilidadeSubcategoria;
import util.JsfUtil;

@Named("formCadastroMovimentoContabilSubcategoria")
@ViewScoped
public class FormCadastroMovimentoContabilSubcategoria implements Serializable {

// ------------------------------------------------------------------ ATRIBUTOS
    private ContabilidadeSubcategoria item;
    private List<ContabilidadeSubcategoria> lista;
    private List<ContabilidadeSubcategoria> itensSelecionados;
    private List<ContabilidadeCategoria> listaDeCategorias;
    private boolean inabilitaSelecaoCategoria;

// ---------------------------------------------------------------- CONSTRUCTOR    
    public FormCadastroMovimentoContabilSubcategoria() {
        geraLista();
    }

    private void geraLista() {

        ContabilidadeSubcategoria consulta = new ContabilidadeSubcategoria();

        List<Order> ordem = new ArrayList<>();
        ordem.add(Order.asc("descricao"));

        lista = consulta.obterLista(null, ordem);
    }

// --------------------------------------------- GETTERS E SETTERS DESTA CLASSE
    public ContabilidadeSubcategoria getItem() {
        return item;
    }

    public void setItem(ContabilidadeSubcategoria item) {
        this.item = item;
    }

    public List<ContabilidadeSubcategoria> getLista() {
        return lista;
    }

    public void setLista(List<ContabilidadeSubcategoria> lista) {
        this.lista = lista;
    }

    public List<ContabilidadeSubcategoria> getItensSelecionados() {
        return itensSelecionados;
    }

    public void setItensSelecionados(List<ContabilidadeSubcategoria> itensSelecionados) {
        this.itensSelecionados = itensSelecionados;
    }

    public List<ContabilidadeCategoria> getListaDeCategorias() {
        ContabilidadeCategoria consulta = new ContabilidadeCategoria();
        List<Order> ordem = new ArrayList<>();
        ordem.add(Order.asc("descricao"));
        listaDeCategorias = consulta.obterLista(null, ordem);

        return listaDeCategorias;
    }

    public void setListaDeCredores(List<ContabilidadeCategoria> listaDeCategorias) {
        this.listaDeCategorias = listaDeCategorias;
    }

    public boolean isInabilitaSelecaoCategoria() {
        return inabilitaSelecaoCategoria;
    }

    public void setInabilitaSelecaoCategoria(boolean inabilitaSelecaoCategoria) {
        this.inabilitaSelecaoCategoria = inabilitaSelecaoCategoria;
    }

// ----------------------------------------------------- MÉTODOS PARA PERSISTIR
    public void criaNovo() {
        item = new ContabilidadeSubcategoria();
    }

    public void edita(ContabilidadeSubcategoria itemSelecionado) {
        item = itemSelecionado;
        setInabilitaSelecaoCategoria(item.getContaContabilMovimentoCollection().size() > 0);
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
                for (ContabilidadeSubcategoria itemSelecionado : itensSelecionados) {
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
                for (ContabilidadeSubcategoria itemSelecionado : itensSelecionados) {
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
