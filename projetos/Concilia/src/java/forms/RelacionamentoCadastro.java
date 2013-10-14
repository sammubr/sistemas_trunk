/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import controls.ContaBancaria;
import controls.ContaContabil;
import controls.RelContabilidadeBanco;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.model.CollectionDataModel;
import javax.faces.model.DataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import util.JsfUtil;

@Named("relacionamentoCadastro")
@ViewScoped
public class RelacionamentoCadastro implements Serializable {

    private RelContabilidadeBanco fRelacionamento;
    private DataModel fListaDeRelacionamentos;
    private List<ContaBancaria> fListaDeContasBancarias;
    private List<ContaContabil> fListaDeContasContabeis;
    private List<ContaBancaria> fListaDeContasBancariasExcluidas;
    private List<ContaContabil> fListaDeContasContabeisExcluidas;
    private boolean fGridVisivel;
    private boolean fItemVisivel;

    @PostConstruct
    public void abreForm() {
        geraListaDeRelacionamentos();
        mostraGrid();
    }

    public RelContabilidadeBanco getRelacionamento() {
        return fRelacionamento;
    }

    public void setRelacionamento(RelContabilidadeBanco relacionamento) {
        this.fRelacionamento = relacionamento;
    }

    public DataModel getListaDeRelacionamentos() {
        return fListaDeRelacionamentos;
    }

    public void setListaDeRelacionamentos(DataModel listaRelacionamentos) {
        this.fListaDeRelacionamentos = listaRelacionamentos;
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

    public List<ContaContabil> getListaDeContasContabeis() {
        return fListaDeContasContabeis;
    }

    public void setListaDeContasContabeis(List<ContaContabil> listaDeContasContabeis) {
        this.fListaDeContasContabeis = listaDeContasContabeis;
    }

    public List<ContaBancaria> getListaDeContasBancarias() {
        return fListaDeContasBancarias;
    }

    public void setListaDeContasBancarias(List<ContaBancaria> listaDeContasBancarias) {
        this.fListaDeContasBancarias = listaDeContasBancarias;
    }

    private void geraListaDeRelacionamentos() {
        List<String> ordem = new ArrayList<>();
        ordem.add("descricao");
        RelContabilidadeBanco relacionamento = new RelContabilidadeBanco();
        this.fListaDeRelacionamentos = new CollectionDataModel(relacionamento.obter(null, null, ordem));
    }

    public void mostraGrid() {
        setGridVisivel(true);
        setItemVisivel(false);
    }

    public void mostraItem() {
        setGridVisivel(false);
        setItemVisivel(true);

    }

    public void criaNovo() {
        fRelacionamento = new RelContabilidadeBanco();
        fListaDeContasContabeis = new ArrayList<>();
        fListaDeContasBancarias = new ArrayList<>();
        mostraItem();
    }

    public void edita() {
        setRelacionamento((RelContabilidadeBanco) getListaDeRelacionamentos().getRowData());
        fListaDeContasContabeis = (List<ContaContabil>) getRelacionamento().getContaContabilCollection();
        fListaDeContasBancarias = (List<ContaBancaria>) getRelacionamento().getContaBancariaCollection();
        mostraItem();
    }

    public void persiste() {

        if (validaDadosInseridos()) {

            fRelacionamento.setContaBancariaCollection(fListaDeContasBancarias);
            fRelacionamento.setContaContabilCollection(fListaDeContasContabeis);
            fRelacionamento.persiste();

            for (ContaContabil conta : fListaDeContasContabeis) {
                conta.setRelContabilidadeBanco(fRelacionamento);
                conta.persiste();
            }

            for (ContaBancaria conta : fListaDeContasBancarias) {
                conta.setRelContabilidadeBanco(fRelacionamento);
                conta.persiste();
            }

            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"), "");
            geraListaDeRelacionamentos();
            mostraGrid();
        } else {
            JsfUtil.addErrorMessage("Erro ao gravar relacionamento!", "É necessário selecionar ao menos uma conta contábil e uma conta bancária");

        }
    }

    public void exclui() {
        fRelacionamento = (RelContabilidadeBanco) getListaDeRelacionamentos().getRowData();
        fRelacionamento.exclui();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"), "");
        geraListaDeRelacionamentos();
    }

    public void addContaBancaria(ContaBancaria conta) {
        if (fListaDeContasBancarias.contains(conta)) {
            JsfUtil.addErrorMessage("Erro!", "A conta já está selecionada!");
        } else {
            fListaDeContasBancarias.add(conta);
            JsfUtil.addSuccessMessage("Conta selecionada!", "Descrição: " + conta.getDescricao());
        }
    }

    public void removeContaBancaria(ContaBancaria conta) {
        fListaDeContasBancarias.remove(conta);
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"), "");
    }

    public void addContaContabil(ContaContabil conta) {
        if (fListaDeContasContabeis.contains(conta)) {
            JsfUtil.addErrorMessage("Erro!", "A conta já está selecionada!");
        } else {
            fListaDeContasContabeis.add(conta);
            JsfUtil.addSuccessMessage("Conta selecionada!", "Descrição: " + conta.getDescricao());
        }
    }

    public void removeContaContabil(ContaContabil conta) {
        fListaDeContasContabeis.remove(conta);
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"), "");
    }

    public DataModel contasContabeisSemRelacionamento() {

        List<String> atributos = new ArrayList<>();
        atributos.add("relContabilidadeBanco");

        List<Object> valores = new ArrayList<>();
        valores.add(null);

        List<String> ordem = new ArrayList<>();
        ordem.add("descricao");

        ContaContabil conta = new ContaContabil();
        DataModel lista = new CollectionDataModel(conta.obter(atributos, valores, ordem));
        return lista;
    }

    public DataModel contasBancariasSemRelacionamento() {

        List<String> atributos = new ArrayList<>();
        atributos.add("relContabilidadeBanco");

        List<Object> valores = new ArrayList<>();
        valores.add(null);

        List<String> ordem = new ArrayList<>();
        ordem.add("descricao");

        ContaBancaria conta = new ContaBancaria();
        DataModel lista = new CollectionDataModel(conta.obter(atributos, valores, ordem));
        return lista;
    }

    public void onContaBancariaChosen(SelectEvent event) {
        ContaBancaria conta = (ContaBancaria) event.getObject();
        addContaBancaria(conta);
    }

    public void onContaContabilChosen(SelectEvent event) {
        ContaContabil conta = (ContaContabil) event.getObject();
        addContaContabil(conta);
    }

    private boolean validaDadosInseridos() {
        return fListaDeContasContabeis.size() > 0 && fListaDeContasBancarias.size() > 0;
    }

    public List<ContaBancaria> getfListaDeContasBancariasExcluidas() {
        return fListaDeContasBancariasExcluidas;
    }

    public void setfListaDeContasBancariasExcluidas(List<ContaBancaria> fListaDeContasBancariasExcluidas) {
        this.fListaDeContasBancariasExcluidas = fListaDeContasBancariasExcluidas;
    }

    public List<ContaContabil> getfListaDeContasContabeisExcluidas() {
        return fListaDeContasContabeisExcluidas;
    }

    public void setfListaDeContasContabeisExcluidas(List<ContaContabil> fListaDeContasContabeisExcluidas) {
        this.fListaDeContasContabeisExcluidas = fListaDeContasContabeisExcluidas;
    }
}