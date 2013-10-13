/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import diversos.ArquivoMovimentoContaBancaria;
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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
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
    private UploadedFile file;

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

    public void handleContaChange() {
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
        valores.add(fContaBancariaSelecionada);

        List<String> ordem = new ArrayList<>();
        ordem.add("dataMov");
        ordem.add("idcontaBancariaMovimento");
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
        fContaBancariaMovimento.persiste();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"), "");
        filtraListaMovimentos();
        mostraGrid();

    }

    public void exclui() {
        fContaBancariaMovimento = (ContaBancariaMovimento) getListaMovimentos().getRowData();
        fContaBancariaMovimento.exclui();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"), "");
        filtraListaMovimentos();

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

    public void upload(FileUploadEvent event) {

        try {
            if (contaSelecionada()) {
                file = event.getFile();
                ArquivoMovimentoContaBancaria arquivo = new ArquivoMovimentoContaBancaria();
                arquivo.importaMovimentacao(fContaBancariaSelecionada, file);
            }
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("ConverterDataErro"), ex);
        }
    }

    private boolean contaSelecionada() {

        if (fContaBancariaSelecionada == null) {
            JsfUtil.addErrorMessage("Erro ao importar arquivo!", "Nenhuma conta selecionada");
            return false;
        } else {
            if (fContaBancariaSelecionada.getTagData().isEmpty()
                    || fContaBancariaSelecionada.getTagFimMovimento().isEmpty()
                    || fContaBancariaSelecionada.getTagHistorico().isEmpty()
                    || fContaBancariaSelecionada.getTagInicioMovimento().isEmpty()
                    || fContaBancariaSelecionada.getTagNumDoc().isEmpty()
                    || fContaBancariaSelecionada.getTagValor().isEmpty()) {
                JsfUtil.addErrorMessage("Erro ao importar arquivo!", "Para importar um arquivo, é necessário preencher todas as tags no cadastro de contas bancárias.");
                return false;
            } else {
                return true;
            }
        }
    }
}
