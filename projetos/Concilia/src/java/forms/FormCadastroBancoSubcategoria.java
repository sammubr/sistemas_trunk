package forms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.hibernate.criterion.Order;
import org.primefaces.context.RequestContext;
import tabelas.BancoSubcategoria;
import util.JsfUtil;

@Named("formCadastroBancoSubcategoria")
@ViewScoped
public class FormCadastroBancoSubcategoria implements Serializable {

// ------------------------------------------------------------------ ATRIBUTOS
    private BancoSubcategoria item;
    private List<BancoSubcategoria> lista;
    private List<BancoSubcategoria> itensSelecionados;

// ---------------------------------------------------------------- CONSTRUCTOR    
    public FormCadastroBancoSubcategoria() {
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
                    itemSelecionado.exclui();
                }
                geraLista();
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"), "");
                break;
            default:
                for (BancoSubcategoria itemSelecionado : itensSelecionados) {
                    itemSelecionado.exclui();
                }
                geraLista();
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordsDeleted"), "");
                break;
        }
    }
}