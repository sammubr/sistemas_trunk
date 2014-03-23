package forms;

import tabelas.ContaBancaria;
import tabelas.ContaBancariaMovimento;
import diversos.ArquivoMovimentoContaBancaria;
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

@Named("contaBancariaMovimentoCadastro")
@ViewScoped
public class FormCadastroContaBancariaMovimento implements Serializable {

// ------------------------------------------------------------------ ATRIBUTOS
    private ContaBancariaMovimento item;
    private List<ContaBancariaMovimento> lista;
    private List<ContaBancariaMovimento> itensSelecionados;
    private ContaBancaria contaBancariaSelecionada;
    private List<ContaBancaria> listaDeContasBancarias;
    private UploadedFile file;

// ---------------------------------------------------------------- CONSTRUCTOR    
    public FormCadastroContaBancariaMovimento() {
        geraListaDeContasBancarias();
    }

    private void geraListaDeContasBancarias() {
        ContaBancaria contaBancaria = new ContaBancaria();
        List<Order> ordem = new ArrayList<>();        
        ordem.add(Order.asc("descricao"));
        setListaDeContasBancarias(contaBancaria.obterLista(null, ordem));
    }

// --------------------------------------------- GETTERS E SETTERS DESTA CLASSE
    public ContaBancariaMovimento getItem() {
        return item;
    }

    public void setItem(ContaBancariaMovimento item) {
        this.item = item;
    }

    public List<ContaBancariaMovimento> getLista() {
        return lista;
    }

    public void setLista(List<ContaBancariaMovimento> lista) {
        this.lista = lista;
    }

    public List<ContaBancariaMovimento> getItensSelecionados() {
        return itensSelecionados;
    }

    public void setItensSelecionados(List<ContaBancariaMovimento> itensSelecionados) {
        this.itensSelecionados = itensSelecionados;
    }

    public ContaBancaria getContaBancariaSelecionada() {
        return contaBancariaSelecionada;
    }

    public void setContaBancariaSelecionada(ContaBancaria contaBancariaSelecionada) {
        this.contaBancariaSelecionada = contaBancariaSelecionada;
    }

    public List<ContaBancaria> getListaDeContasBancarias() {
        return listaDeContasBancarias;
    }

    public void setListaDeContasBancarias(List<ContaBancaria> listaDeContasBancarias) {
        this.listaDeContasBancarias = listaDeContasBancarias;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

// ----------------------------------------------------- MÉTODOS PARA PERSISTIR
    public void criaNovo() {
        item = new ContaBancariaMovimento();
    }

    public void edita(ContaBancariaMovimento itemSelecionado) {
        item = itemSelecionado;
    }

    public void persiste() {
        getItem().setConta(contaBancariaSelecionada);
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
                for (ContaBancariaMovimento itemSelecionado : itensSelecionados) {
                    itemSelecionado.exclui();
                }
                geraListaDeMovimentos();
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"), "");
                break;
            default:
                for (ContaBancariaMovimento itemSelecionado : itensSelecionados) {
                    itemSelecionado.exclui();
                }
                geraListaDeMovimentos();
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordsDeleted"), "");
                break;
        }
    }

    public void geraListaDeMovimentos() {

        ContaBancariaMovimento consulta = new ContaBancariaMovimento();
                
        List<Criterion> filtro = new ArrayList<>();
        filtro.add(Restrictions.eq("conta", contaBancariaSelecionada));
        List<Order> ordem = new ArrayList<>();
        ordem.add(Order.asc("dataMov"));
        ordem.add(Order.asc("idcontaBancariaMovimento"));        
        
        lista = consulta.obterLista(filtro, ordem);
    }

    public void upload(FileUploadEvent event) {

        file = event.getFile();
        ArquivoMovimentoContaBancaria arquivo = new ArquivoMovimentoContaBancaria();
        try {
            arquivo.importaMovimentacao(contaBancariaSelecionada, file);
            geraListaDeMovimentos();
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("LeituraArquivoErro"), ex);
        }
    }
}