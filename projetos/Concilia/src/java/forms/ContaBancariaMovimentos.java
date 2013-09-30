/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import controls.ContaBancaria;
import controls.ContaBancariaMovimento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.model.CollectionDataModel;
import javax.faces.model.DataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import util.JsfUtil;

@Named("contaBancariaMovimentos")
@ViewScoped
public class ContaBancariaMovimentos implements Serializable {

    private List<ContaBancaria> fListaDeContasBancarias;
    private ContaBancaria fContaBancariaSelecionada;
    private DataModel fListaDeMovimentos;
    private boolean fGridVisivel;
    private boolean fItemVisivel;
    private boolean fContaSelecionada = false;
    private ContaBancariaMovimento fContaBancariaMovimento;
    private String fArquivoDeMovimento;

    @PostConstruct
    public void abreForm() {
        geraListaDeContasBancarias();
        mostraGrid();
    }

    public List<ContaBancaria> getListaContasBancarias() {
        return fListaDeContasBancarias;
    }

    public void setListaContasBancarias(List<ContaBancaria> listaContasBancarias) {
        this.fListaDeContasBancarias = listaContasBancarias;
    }

    public ContaBancaria getContaBancariaSelecionada() {
        return fContaBancariaSelecionada;
    }

    public void setContaBancariaSelecionada(ContaBancaria contaBancariaSelecionada) {
        setContaSelecionada(contaBancariaSelecionada != null);
        this.fContaBancariaSelecionada = contaBancariaSelecionada;
        filtraListaMovimentos();

    }

    public DataModel getListaMovimentos() {
        return fListaDeMovimentos;
    }

    public void setListaMovimentos(DataModel listaMovimentos) {
        this.fListaDeMovimentos = listaMovimentos;
    }

    private void filtraListaMovimentos() {

        List<String> atributos = new ArrayList<>();
        atributos.add("conta");

        List<Object> valores = new ArrayList<>();
        valores.add(fContaBancariaSelecionada);

        List<String> ordem = new ArrayList<>();
        ordem.add("dataMov");
        ContaBancariaMovimento contaBancariaMovimento = new ContaBancariaMovimento();
        this.fListaDeMovimentos = new CollectionDataModel(contaBancariaMovimento.obter(atributos, valores, ordem));
    }

    public void mostraGrid() {
        setGridVisivel(true);
        setItemVisivel(false);
    }

    public void mostraItem() {
        setGridVisivel(false);
        setItemVisivel(true);
    }

    public boolean isGridVisivel() {
        return fGridVisivel;
    }

    public void setGridVisivel(boolean gridVisivel) {
        this.fGridVisivel = gridVisivel;
    }

    public boolean isItemVisivel() {
        return fItemVisivel;
    }

    public void setItemVisivel(boolean itemVisivel) {
        this.fItemVisivel = itemVisivel;
    }

    public void edita() {
        setContaBancariaMovimento((ContaBancariaMovimento) getListaMovimentos().getRowData());
        mostraItem();
    }

    public ContaBancariaMovimento getContaBancariaMovimento() {
        return fContaBancariaMovimento;
    }

    public void setContaBancariaMovimento(ContaBancariaMovimento contaBancariaMovimento) {
        this.fContaBancariaMovimento = contaBancariaMovimento;
    }

    public void persiste() {
        if (fContaBancariaMovimento.persiste()) {
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"));
            filtraListaMovimentos();
            mostraGrid();
        }
    }

    public void exclui() {
        fContaBancariaMovimento = (ContaBancariaMovimento) getListaMovimentos().getRowData();
        if (fContaBancariaMovimento.exclui()) {
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"));
            filtraListaMovimentos();
        }
    }

    public void criaNovo() {
        fContaBancariaMovimento = new ContaBancariaMovimento();
        fContaBancariaMovimento.setConta(fContaBancariaSelecionada);
        mostraItem();
    }

    public boolean isContaSelecionada() {
        return fContaSelecionada;
    }

    public void setContaSelecionada(boolean contaSelecionada) {
        this.fContaSelecionada = contaSelecionada;
    }

    private void geraListaDeContasBancarias() {
        List<String> ordem = new ArrayList<>();
        ordem.add("descricao");
        ContaBancaria contaBancaria = new ContaBancaria();
        this.fListaDeContasBancarias = (List) contaBancaria.obter(null, null, ordem);
        
    }

    /**
     * @return the arquivoDeMovimentos
     */
    public String getArquivoDeMovimentos() {
        return fArquivoDeMovimento;
    }

    /**
     * @param arquivoDeMovimentos the arquivoDeMovimentos to set
     */
    public void setArquivoDeMovimentos(String arquivoDeMovimentos) {
        this.fArquivoDeMovimento = arquivoDeMovimentos;
    }
    
    //----------------------------------------------------------------------
    
    private Part file;

    /**
     * @return the file
     */
    public Part getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(Part file) {
        this.file = file;
    }

    /**
     * @return the fileContent
     */
   
    public void upload() {
        ArquivoDeMovimento arquivo = new ArquivoDeMovimento();
        arquivo.importaMovimentacao(fContaBancariaSelecionada,file);
    }
    }
