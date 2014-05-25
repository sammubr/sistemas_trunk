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
import tabelas.BancoSubcategoria;
import util.JsfUtil;

@Named("formCadastroMovimentoBancarioSubcategoria")
@ViewScoped
public class FormCadastroMovimentoBancarioSubcategoria implements Serializable {

// ------------------------------------------------------------------ ATRIBUTOS
    private BancoSubcategoria item;
    private List<BancoSubcategoria> lista;
    private List<BancoSubcategoria> itensSelecionados;
    private List<BancoCategoria> listaDeCategorias;

// ---------------------------------------------------------------- CONSTRUCTOR    
    public FormCadastroMovimentoBancarioSubcategoria() {
        geraLista();
    }

    private void geraLista() {

        BancoSubcategoria consulta = new BancoSubcategoria();

        List<Order> ordem = new ArrayList<>();
        ordem.add(Order.asc("descricao"));

        lista = consulta.obterLista(null, ordem);
    }

// --------------------------------------------- GETTERS E SETTERS DESTA CLASSE
    public BancoSubcategoria getItem() {
        return item;
    }

    public void setItem(BancoSubcategoria item) {
        this.item = item;
    }

    public List<BancoSubcategoria> getLista() {
        return lista;
    }

    public void setLista(List<BancoSubcategoria> lista) {
        this.lista = lista;
    }

    public List<BancoSubcategoria> getItensSelecionados() {
        return itensSelecionados;
    }

    public void setItensSelecionados(List<BancoSubcategoria> itensSelecionados) {
        this.itensSelecionados = itensSelecionados;
    }

    public List<BancoCategoria> getListaDeCategorias() {
        BancoCategoria consulta = new BancoCategoria();
        List<Order> ordem = new ArrayList<>();        
        ordem.add(Order.asc("descricao"));
        listaDeCategorias = consulta.obterLista(null, ordem);

        return listaDeCategorias;
    }

    public void setListaDeCredores(List<BancoCategoria> listaDeCategorias) {
        this.listaDeCategorias = listaDeCategorias;
    }    
    
// ----------------------------------------------------- MÉTODOS PARA PERSISTIR
    public void criaNovo() {
        item = new BancoSubcategoria();
    }

    public void edita(BancoSubcategoria itemSelecionado) {
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
                for (BancoSubcategoria itemSelecionado : itensSelecionados) {
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
                for (BancoSubcategoria itemSelecionado : itensSelecionados) {
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
