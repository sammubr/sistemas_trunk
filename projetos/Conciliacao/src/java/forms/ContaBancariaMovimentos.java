/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import controls.ContaBancaria;
import controls.ContaBancariaMovimento;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.model.CollectionDataModel;
import javax.faces.model.DataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import persistencia.ConsultaGeral;
import util.JsfUtil;

@Named("contaBancariaMovimentos")
@ViewScoped
public class ContaBancariaMovimentos implements Serializable {

    private List<ContaBancaria> listaContasBancarias;
    private ContaBancaria contaBancariaSelecionada;
    private DataModel listaMovimentos;
    private boolean gridVisivel;
    private boolean itemVisivel;
    private boolean contaSelecionada = false;
    private ContaBancariaMovimento contaBancariaMovimento;
    private String arquivoDeMovimentos;

    @PostConstruct
    public void abreForm() {
        geraListaDeContasBancarias();
        mostraGrid();
    }

    public List<ContaBancaria> getListaContasBancarias() {
        return listaContasBancarias;
    }

    public void setListaContasBancarias(List<ContaBancaria> listaContasBancarias) {
        this.listaContasBancarias = listaContasBancarias;
    }

    public ContaBancaria getContaBancariaSelecionada() {
        return contaBancariaSelecionada;
    }

    public void setContaBancariaSelecionada(ContaBancaria contaBancariaSelecionada) {
        setContaSelecionada(contaBancariaSelecionada != null);
        this.contaBancariaSelecionada = contaBancariaSelecionada;
        filtraListaMovimentos();

    }

    public DataModel getListaMovimentos() {
        return listaMovimentos;
    }

    public void setListaMovimentos(DataModel listaMovimentos) {
        this.listaMovimentos = listaMovimentos;
    }

    private void filtraListaMovimentos() {
        this.listaMovimentos = new CollectionDataModel(ConsultaGeral.consulta(ContaBancariaMovimento.class, "conta", contaBancariaSelecionada, "dataMov"));
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
        return gridVisivel;
    }

    public void setGridVisivel(boolean gridVisivel) {
        this.gridVisivel = gridVisivel;
    }

    public boolean isItemVisivel() {
        return itemVisivel;
    }

    public void setItemVisivel(boolean itemVisivel) {
        this.itemVisivel = itemVisivel;
    }

    public void edita() {
        setContaBancariaMovimento((ContaBancariaMovimento) getListaMovimentos().getRowData());
        mostraItem();
    }

    public ContaBancariaMovimento getContaBancariaMovimento() {
        return contaBancariaMovimento;
    }

    public void setContaBancariaMovimento(ContaBancariaMovimento contaBancariaMovimento) {
        this.contaBancariaMovimento = contaBancariaMovimento;
    }

    public void persiste() {
        if (contaBancariaMovimento.persiste()) {
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"));
            filtraListaMovimentos();
            mostraGrid();
        }
    }

    public void exclui() {
        contaBancariaMovimento = (ContaBancariaMovimento) getListaMovimentos().getRowData();
        if (contaBancariaMovimento.exclui()) {
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"));
            filtraListaMovimentos();
        }
    }

    public void criaNovo() {
        contaBancariaMovimento = new ContaBancariaMovimento();
        contaBancariaMovimento.setConta(contaBancariaSelecionada);
        mostraItem();
    }

    public boolean isContaSelecionada() {
        return contaSelecionada;
    }

    public void setContaSelecionada(boolean contaSelecionada) {
        this.contaSelecionada = contaSelecionada;
    }

    private void geraListaDeContasBancarias() {
        this.listaContasBancarias = (List) ConsultaGeral.consultaTodos(ContaBancaria.class, "descricao");
    }

    /**
     * @return the arquivoDeMovimentos
     */
    public String getArquivoDeMovimentos() {
        return arquivoDeMovimentos;
    }

    /**
     * @param arquivoDeMovimentos the arquivoDeMovimentos to set
     */
    public void setArquivoDeMovimentos(String arquivoDeMovimentos) {
        this.arquivoDeMovimentos = arquivoDeMovimentos;
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
        ArquivoDeMovimento arquivo = new ArquivoDeMovimento(file);        
    }
}