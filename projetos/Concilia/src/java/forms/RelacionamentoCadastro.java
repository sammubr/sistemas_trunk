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
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import util.JsfUtil;

@Named("relacionamentoCadastro")
@ViewScoped
public class RelacionamentoCadastro implements Serializable {

// ------------------------------------------------------------------ ATRIBUTOS
    private RelContabilidadeBanco relacionamento;
    private List<RelContabilidadeBanco> listaDeRelacionamentos;
    private List<RelContabilidadeBanco> relacionamentosSelecionados;
    private List<ContaContabil> listaDeContasContabeisDoRelacionamento;
    private List<ContaContabil> listaDeContasContabeisDoRelacionamentoSelecionadas;
    private List<ContaBancaria> listaDeContasBancariasDoRelacionamento;
    private List<ContaBancaria> listaDeContasBancariasDoRelacionamentoSelecionadas;
    private boolean gridVisivel = true;
    private List<ContaContabil> listaDeContasContabeis;
    private List<ContaContabil> listaDeContasContabeisSelecionadas;
    private List<ContaBancaria> listaDeContasBancarias;
    private List<ContaBancaria> listaDeContasBancariasSelecionadas;
    private List<ContaContabil> contasContabeisExcluidasDoRelacionamento;
    private List<ContaBancaria> contasBancariasExcluidasDoRelacionamento;

// ---------------------------------------------------------------- CONSTRUCTOR    
    public RelacionamentoCadastro() {
        geraListaDeRelacionamentos();
    }

    private void geraListaDeRelacionamentos() {
        List<String> ordem = new ArrayList<>();
        ordem.add("descricao");
        RelContabilidadeBanco consulta = new RelContabilidadeBanco();
        setListaDeRelacionamentos((List<RelContabilidadeBanco>) (List) consulta.obter(null, null, ordem));
    }

// --------------------------------------------- GETTERS E SETTERS DESTA CLASSE
    public RelContabilidadeBanco getRelacionamento() {
        return relacionamento;
    }

    public void setRelacionamento(RelContabilidadeBanco relacionamento) {
        this.relacionamento = relacionamento;
    }

    public List<RelContabilidadeBanco> getListaDeRelacionamentos() {
        return listaDeRelacionamentos;
    }

    public void setListaDeRelacionamentos(List<RelContabilidadeBanco> listaDeRelacionamentos) {
        this.listaDeRelacionamentos = listaDeRelacionamentos;
    }

    public List<RelContabilidadeBanco> getRelacionamentosSelecionados() {
        return relacionamentosSelecionados;
    }

    public void setRelacionamentosSelecionados(List<RelContabilidadeBanco> relacionamentosSelecionados) {
        this.relacionamentosSelecionados = relacionamentosSelecionados;
    }

    public boolean isGridVisivel() {
        return gridVisivel;
    }

    public void setGridVisivel(boolean gridVisivel) {
        this.gridVisivel = gridVisivel;
    }

    public List<ContaContabil> getListaDeContasContabeis() {
        return listaDeContasContabeis;
    }

    public void setListaDeContasContabeis(List<ContaContabil> listaDeContasContabeis) {
        this.listaDeContasContabeis = listaDeContasContabeis;
    }

    public List<ContaContabil> getListaDeContasContabeisSelecionadas() {
        return listaDeContasContabeisSelecionadas;
    }

    public void setListaDeContasContabeisSelecionadas(List<ContaContabil> listaDeContasContabeisSelecionadas) {
        this.listaDeContasContabeisSelecionadas = listaDeContasContabeisSelecionadas;
    }

    public List<ContaBancaria> getListaDeContasBancarias() {
        return listaDeContasBancarias;
    }

    public void setListaDeContasBancarias(List<ContaBancaria> listaDeContasBancarias) {
        this.listaDeContasBancarias = listaDeContasBancarias;
    }

    public List<ContaBancaria> getListaDeContasBancariasSelecionadas() {
        return listaDeContasBancariasSelecionadas;
    }

    public void setListaDeContasBancariasSelecionadas(List<ContaBancaria> listaDeContasBancariasSelecionadas) {
        this.listaDeContasBancariasSelecionadas = listaDeContasBancariasSelecionadas;
    }

    public List<ContaContabil> getListaDeContasContabeisDoRelacionamento() {
        return listaDeContasContabeisDoRelacionamento;
    }

    public void setListaDeContasContabeisDoRelacionamento(List<ContaContabil> listaDeContasContabeisDoRelacionamento) {
        this.listaDeContasContabeisDoRelacionamento = listaDeContasContabeisDoRelacionamento;
    }

    public List<ContaContabil> getListaDeContasContabeisDoRelacionamentoSelecionadas() {
        return listaDeContasContabeisDoRelacionamentoSelecionadas;
    }

    public void setListaDeContasContabeisDoRelacionamentoSelecionadas(List<ContaContabil> listaDeContasContabeisDoRelacionamentoSelecionadas) {
        this.listaDeContasContabeisDoRelacionamentoSelecionadas = listaDeContasContabeisDoRelacionamentoSelecionadas;
    }

    public List<ContaBancaria> getListaDeContasBancariasDoRelacionamento() {
        return listaDeContasBancariasDoRelacionamento;
    }

    public void setListaDeContasBancariasDoRelacionamento(List<ContaBancaria> listaDeContasBancariasDoRelacionamento) {
        this.listaDeContasBancariasDoRelacionamento = listaDeContasBancariasDoRelacionamento;
    }

    public List<ContaBancaria> getListaDeContasBancariasDoRelacionamentoSelecionadas() {
        return listaDeContasBancariasDoRelacionamentoSelecionadas;
    }

    public void setListaDeContasBancariasDoRelacionamentoSelecionadas(List<ContaBancaria> listaDeContasBancariasDoRelacionamentoSelecionadas) {
        this.listaDeContasBancariasDoRelacionamentoSelecionadas = listaDeContasBancariasDoRelacionamentoSelecionadas;
    }

    public List<ContaContabil> getContasContabeisExcluidasDoRelacionamento() {
        return contasContabeisExcluidasDoRelacionamento;
    }

    public void setContasContabeisExcluidasDoRelacionamento(List<ContaContabil> contasContabeisExcluidasDoRelacionamento) {
        this.contasContabeisExcluidasDoRelacionamento = contasContabeisExcluidasDoRelacionamento;
    }

    public List<ContaBancaria> getContasBancariasExcluidasDoRelacionamento() {
        return contasBancariasExcluidasDoRelacionamento;
    }

    public void setContasBancariasExcluidasDoRelacionamento(List<ContaBancaria> contasBancariasExcluidasDoRelacionamento) {
        this.contasBancariasExcluidasDoRelacionamento = contasBancariasExcluidasDoRelacionamento;
    }

// ----------------------------------------------------- MÉTODOS PARA PERSISTIR
    public void criaNovo() {
        relacionamento = new RelContabilidadeBanco();
        listaDeContasContabeisDoRelacionamento = new ArrayList<>();
        listaDeContasBancariasDoRelacionamento = new ArrayList<>();
        contasContabeisExcluidasDoRelacionamento = new ArrayList<>();
        contasBancariasExcluidasDoRelacionamento = new ArrayList<>();
        setGridVisivel(false);
    }

    public void edita(RelContabilidadeBanco itemSelecionado) {
        relacionamento = itemSelecionado;
        listaDeContasContabeisDoRelacionamento = (List<ContaContabil>) itemSelecionado.getContaContabilCollection();
        listaDeContasBancariasDoRelacionamento = (List<ContaBancaria>) itemSelecionado.getContaBancariaCollection();
        contasContabeisExcluidasDoRelacionamento = new ArrayList<>();
        contasBancariasExcluidasDoRelacionamento = new ArrayList<>();
        setGridVisivel(false);
    }

    public void persiste() {
        getRelacionamento().persiste();
        persisteListsRelacionados();
        geraListaDeRelacionamentos();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordSaved"), "");
        setGridVisivel(true);
    }

    private void persisteListsRelacionados() {

        for (ContaContabil contaContabil : listaDeContasContabeisDoRelacionamento) {
            contaContabil.setRelContabilidadeBanco(getRelacionamento());
            contaContabil.persiste();
        }

        for (ContaBancaria contaBancaria : listaDeContasBancariasDoRelacionamento) {
            contaBancaria.setRelContabilidadeBanco(getRelacionamento());
            contaBancaria.persiste();
        }

        for (ContaContabil contaContabil : contasContabeisExcluidasDoRelacionamento) {
            contaContabil.setRelContabilidadeBanco(null);
            contaContabil.persiste();
        }

        for (ContaBancaria contaBancaria : contasBancariasExcluidasDoRelacionamento) {
            contaBancaria.setRelContabilidadeBanco(null);
            contaBancaria.persiste();
        }

    }

    public void exclui() {
        if (relacionamentosSelecionados.isEmpty()) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("EmptyRecordsToDelete"), "");
        } else {
            for (RelContabilidadeBanco itemSelecionado : relacionamentosSelecionados) {

                for (ContaContabil itemSelecionado_contaContabil : itemSelecionado.getContaContabilCollection()) {
                    itemSelecionado_contaContabil.setRelContabilidadeBanco(null);
                    itemSelecionado_contaContabil.persiste();
                }

                for (ContaBancaria itemSelecionado_contaBancaria : itemSelecionado.getContaBancariaCollection()) {
                    itemSelecionado_contaBancaria.setRelContabilidadeBanco(null);
                    itemSelecionado_contaBancaria.persiste();
                }

                itemSelecionado.exclui();
                geraListaDeRelacionamentos();
            }

            if (relacionamentosSelecionados.size() == 1) {
                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordDeleted"), "");

            } else {

                JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecordsDeleted"), "");
            }
        }
    }

    public void cancela() {
        setGridVisivel(true);
    }

    public void addContaContabil() {
        for (ContaContabil pItem : listaDeContasContabeisSelecionadas) {
            if (pItem.getRelContabilidadeBanco() == null) {
                if (listaDeContasContabeisDoRelacionamento.contains(pItem)) {
                    JsfUtil.addErrorMessage("Erro!", "A conta já está selecionada!");
                } else {
                    listaDeContasContabeisDoRelacionamento.add(pItem);
                    if (contasContabeisExcluidasDoRelacionamento.contains(pItem)) {
                        contasContabeisExcluidasDoRelacionamento.remove(pItem);
                    }
                    JsfUtil.addSuccessMessage("Conta selecionada!", "Descrição: " + pItem.getDescricao());
                    RequestContext.getCurrentInstance().execute("$('#modalContasContabeis').modal('hide')");
                }
            } else {
                JsfUtil.addErrorMessage("Erro!", "A conta já está vinculada a um relacionamento!");
            }
        }
    }

    public void addContaBancaria() {
        for (ContaBancaria pItem : getListaDeContasBancariasSelecionadas()) {
            if (pItem.getRelContabilidadeBanco() == null) {
                if (listaDeContasBancariasDoRelacionamento.contains(pItem)) {
                    JsfUtil.addErrorMessage("Erro!", "A conta já está selecionada!");
                } else {
                    listaDeContasBancariasDoRelacionamento.add(pItem);
                    if (contasBancariasExcluidasDoRelacionamento.contains(pItem)) {
                        contasBancariasExcluidasDoRelacionamento.remove(pItem);
                    }
                    JsfUtil.addSuccessMessage("Conta selecionada!", "Descrição: " + pItem.getDescricao());
                    RequestContext.getCurrentInstance().execute("$('#modalContasBancarias').modal('hide')");
                }
            } else {
                JsfUtil.addErrorMessage("Erro!", "A conta já está vinculada a um relacionamento!");
            }
        }
    }

    public void removeContasContabeisDoRelacionamentoSelecionadas() {
        for (ContaContabil pItem : listaDeContasContabeisDoRelacionamentoSelecionadas) {
            listaDeContasContabeisDoRelacionamento.remove(pItem);
            if (!contasContabeisExcluidasDoRelacionamento.contains(pItem)) {
                contasContabeisExcluidasDoRelacionamento.add(pItem);
            }
            JsfUtil.addSuccessMessage("Conta removida com sucesso!", "Descrição: " + pItem.getDescricao());
        }
    }

    public void removeContasBancariasDoRelacionamentoSelecionadas() {
        for (ContaBancaria pItem : listaDeContasBancariasDoRelacionamentoSelecionadas) {
            listaDeContasBancariasDoRelacionamento.remove(pItem);
            if (!contasBancariasExcluidasDoRelacionamento.contains(pItem)) {
                contasBancariasExcluidasDoRelacionamento.add(pItem);
            }
            JsfUtil.addSuccessMessage("Conta removida com sucesso!", "Descrição: " + pItem.getDescricao());
        }
    }

    public void geraListaDeContasContabeis() {
        List<String> ordem = new ArrayList<>();
        ordem.add("descricao");
        ContaContabil conta = new ContaContabil();
        listaDeContasContabeis = (List) conta.obter(null, null, ordem);
        setListaDeContasContabeisSelecionadas(null);
    }

    public void geraListaDeContasBancarias() {
        List<String> ordem = new ArrayList<>();
        ordem.add("descricao");
        ContaBancaria conta = new ContaBancaria();
        listaDeContasBancarias = (List) conta.obter(null, null, ordem);
        setListaDeContasBancariasSelecionadas(null);
    }
}
