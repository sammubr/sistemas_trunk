package forms;

import tabelas.ContaContabil;
import tabelas.ContaContabilMovimento;
import diversos.ArquivoMovimentoContaContabil;
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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import util.JsfUtil;

@Named("formCadastroContaContabilMovimento")
@ViewScoped
public class FormCadastroContaContabilMovimento implements Serializable {

// ------------------------------------------------------------------ ATRIBUTOS
    private ContaContabilMovimento item;
    private List<ContaContabilMovimento> lista;
    private List<ContaContabilMovimento> itensSelecionados;
    private ContaContabil contaContabilSelecionada;
    private List<ContaContabil> listaDeContasContabeis;
    private UploadedFile file;

// ---------------------------------------------------------------- CONSTRUCTOR    
    public FormCadastroContaContabilMovimento() {
        geraListaDeContasContabeis();
    }

    private void geraListaDeContasContabeis() {
        ContaContabil contaContabil = new ContaContabil();
           
        List<Order> ordem = new ArrayList<>();
        ordem.add(Order.asc("descricao"));       
                
        setListaDeContasContabeis(contaContabil.obterLista( null, ordem));
    }

// --------------------------------------------- GETTERS E SETTERS DESTA CLASSE
    public ContaContabilMovimento getItem() {
        return item;
    }

    public void setItem(ContaContabilMovimento item) {
        this.item = item;
    }

    public List<ContaContabilMovimento> getLista() {
        return lista;
    }

    public void setLista(List<ContaContabilMovimento> lista) {
        this.lista = lista;
    }

    public List<ContaContabilMovimento> getItensSelecionados() {
        return itensSelecionados;
    }

    public void setItensSelecionados(List<ContaContabilMovimento> itensSelecionados) {
        this.itensSelecionados = itensSelecionados;
    }

    public ContaContabil getContaContabilSelecionada() {
        return contaContabilSelecionada;
    }

    public void setContaContabilSelecionada(ContaContabil contaContabilSelecionada) {
        this.contaContabilSelecionada = contaContabilSelecionada;
    }

    public List<ContaContabil> getListaDeContasContabeis() {
        return listaDeContasContabeis;
    }

    public void setListaDeContasContabeis(List<ContaContabil> listaDeContasContabeis) {
        this.listaDeContasContabeis = listaDeContasContabeis;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

// ----------------------------------------------------- MÉTODOS PARA PERSISTIR
    public void criaNovo() {
        item = new ContaContabilMovimento();
    }

    public void edita(ContaContabilMovimento itemSelecionado) {
        item = itemSelecionado;
    }

    public void persiste() {
        getItem().setConta(contaContabilSelecionada);
        getItem().persiste();
        geraListaDeMovimentos();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"), "");
        RequestContext.getCurrentInstance().execute("$('#myModal').modal('hide')");
    }

    public void exclui() {
        switch (itensSelecionados.size()) {
            case 0:
                JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("EmptyRecordsToDelete"), "");
                break;
            case 1:
                for (ContaContabilMovimento itemSelecionado : itensSelecionados) {
                    itemSelecionado.exclui();
                }
                geraListaDeMovimentos();
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"), "");
                break;
            default:
                for (ContaContabilMovimento itemSelecionado : itensSelecionados) {
                    itemSelecionado.exclui();
                }
                geraListaDeMovimentos();
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordsDeleted"), "");
                break;
        }
    }

    public void geraListaDeMovimentos() {

        ContaContabilMovimento consulta = new ContaContabilMovimento();
         
        List<Criterion> filtro = new ArrayList<>();
        filtro.add(Restrictions.eq("conta", contaContabilSelecionada));
        List<Order> ordem = new ArrayList<>();
        ordem.add(Order.asc("dataMov"));
        ordem.add(Order.asc("idcontaContabilMovimento"));               
                
        lista = consulta.obterLista(filtro, ordem);
    }

    public void upload(FileUploadEvent event) {

        file = event.getFile();
        ArquivoMovimentoContaContabil arquivo = new ArquivoMovimentoContaContabil();
        try {
            arquivo.importaMovimentacao(contaContabilSelecionada, file);
            geraListaDeMovimentos();
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("LeituraArquivoErro"), ex);
        }
    }
}