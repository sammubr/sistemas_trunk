/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import controls.ContaContabil;
import controls.ContaContabilMovimento;
import diversos.ArquivoMovimentoContaContabil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.model.CollectionDataModel;
import javax.faces.model.DataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;
import util.JsfUtil;

@Named("contaContabilMovimentos")
@ViewScoped
public class ContaContabilMovimentos implements Serializable {

    private List<ContaContabil> fListaDeContasContabeis;
    private ContaContabil fContaContabilSelecionada;
    private DataModel fListaDeMovimentos;
    private boolean fGridVisivel;
    private boolean fItemVisivel;
    private boolean fContaSelecionada = false;
    private ContaContabilMovimento fContaContabilMovimento;
    private String fArquivoDeMovimento;
    private UploadedFile file;

    @PostConstruct
    public void abreForm() {
        geraListaDeContasContabeis();
        mostraGrid();
    }

    public List<ContaContabil> getListaContasContabeis() {
        return fListaDeContasContabeis;
    }

    public void setListaContasContabeis(List<ContaContabil> listaContasContabeis) {
        this.fListaDeContasContabeis = listaContasContabeis;
    }

    public ContaContabil getContaContabilSelecionada() {
        return fContaContabilSelecionada;
    }

    public void setContaContabilSelecionada(ContaContabil contaContabilSelecionada) {
        setContaSelecionada(contaContabilSelecionada != null);
        this.fContaContabilSelecionada = contaContabilSelecionada;
        filtraListaMovimentos();

    }

    public DataModel getListaMovimentos() {
        return fListaDeMovimentos;
    }

    public void setListaMovimentos(DataModel listaMovimentos) {
        this.fListaDeMovimentos = listaMovimentos;
    }
    
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    private void filtraListaMovimentos() {

        List<String> atributos = new ArrayList<>();
        atributos.add("conta");

        List<Object> valores = new ArrayList<>();
        valores.add(fContaContabilSelecionada);

        List<String> ordem = new ArrayList<>();
        ordem.add("dataMov");
        ContaContabilMovimento contaContabilMovimento = new ContaContabilMovimento();
        this.fListaDeMovimentos = new CollectionDataModel(contaContabilMovimento.obter(atributos, valores, ordem));
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
        setContaContabilMovimento((ContaContabilMovimento) getListaMovimentos().getRowData());
        mostraItem();
    }

    public ContaContabilMovimento getContaContabilMovimento() {
        return fContaContabilMovimento;
    }

    public void setContaContabilMovimento(ContaContabilMovimento contaContabilMovimento) {
        this.fContaContabilMovimento = contaContabilMovimento;
    }

    public void persiste() {
        fContaContabilMovimento.persiste();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"));
        filtraListaMovimentos();
        mostraGrid();

    }

    public void exclui() {
        fContaContabilMovimento = (ContaContabilMovimento) getListaMovimentos().getRowData();
        fContaContabilMovimento.exclui();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"));
        filtraListaMovimentos();

    }

    public void criaNovo() {
        fContaContabilMovimento = new ContaContabilMovimento();
        fContaContabilMovimento.setConta(fContaContabilSelecionada);
        mostraItem();
    }

    public boolean isContaSelecionada() {
        return fContaSelecionada;
    }

    public void setContaSelecionada(boolean contaSelecionada) {
        this.fContaSelecionada = contaSelecionada;
    }

    private void geraListaDeContasContabeis() {
        List<String> ordem = new ArrayList<>();
        ordem.add("descricao");
        ContaContabil contaContabil = new ContaContabil();
        this.fListaDeContasContabeis = (List) contaContabil.obter(null, null, ordem);

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

    public void upload() {
        ArquivoMovimentoContaContabil arquivo = new ArquivoMovimentoContaContabil();
        arquivo.importaMovimentacao(fContaContabilSelecionada, file);
    }
}
