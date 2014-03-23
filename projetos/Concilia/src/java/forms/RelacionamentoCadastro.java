/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import tabelas.ContaBancaria;
import tabelas.ContaContabil;
import tabelas.RelContabilidadeBanco;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.hibernate.criterion.Order;
import org.primefaces.context.RequestContext;
import util.JsfUtil;

@Named("relacionamentoCadastro")
@ViewScoped
public class RelacionamentoCadastro implements Serializable {

// ------------------------------------------------------------------ ATRIBUTOS
    private boolean gridVisivel = true;
    private RelContabilidadeBanco relacionamento;
    private List<RelContabilidadeBanco> listaDeRelacionamentos;
    private List<RelContabilidadeBanco> relacionamentosSelecionados;
    private List<ContaContabil> listaDeContasContabeisDoRelacionamentoSelecionadas;
    private List<ContaBancaria> listaDeContasBancariasDoRelacionamentoSelecionadas;
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
        
        RelContabilidadeBanco consulta = new RelContabilidadeBanco();        
        List<Order> ordem = new ArrayList<>();
        ordem.add(Order.asc("descricao"));       
        
        setListaDeRelacionamentos(consulta.obterLista(null, ordem));
    }

// --------------------------------------------- GETTERS E SETTERS DESTA CLASSE
    public boolean isGridVisivel() {
        return gridVisivel;
    }

    public void setGridVisivel(boolean gridVisivel) {
        this.gridVisivel = gridVisivel;
    }

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

    public List<ContaContabil> getListaDeContasContabeisDoRelacionamentoSelecionadas() {
        return listaDeContasContabeisDoRelacionamentoSelecionadas;
    }

    public void setListaDeContasContabeisDoRelacionamentoSelecionadas(List<ContaContabil> listaDeContasContabeisDoRelacionamentoSelecionadas) {
        this.listaDeContasContabeisDoRelacionamentoSelecionadas = listaDeContasContabeisDoRelacionamentoSelecionadas;
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
        setRelacionamento(new RelContabilidadeBanco());
        getRelacionamento().setContaContabilCollection(new ArrayList<ContaContabil>());
        getRelacionamento().setContaBancariaCollection(new ArrayList<ContaBancaria>());
        setContasContabeisExcluidasDoRelacionamento(new ArrayList<ContaContabil>());
        setContasBancariasExcluidasDoRelacionamento(new ArrayList<ContaBancaria>());
        setGridVisivel(false);
    }

    public void edita(RelContabilidadeBanco itemSelecionado) {
        setRelacionamento(itemSelecionado);
        setContasContabeisExcluidasDoRelacionamento(new ArrayList<ContaContabil>());
        setContasBancariasExcluidasDoRelacionamento(new ArrayList<ContaBancaria>());
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

        for (ContaContabil contaContabil : getRelacionamento().getContaContabilCollection()) {
            contaContabil.setRelContabilidadeBanco(getRelacionamento());
            contaContabil.persiste();
        }

        for (ContaBancaria contaBancaria : getRelacionamento().getContaBancariaCollection()) {
            contaBancaria.setRelContabilidadeBanco(getRelacionamento());
            contaBancaria.persiste();
        }

        for (ContaContabil contaContabil : getContasContabeisExcluidasDoRelacionamento()) {
            contaContabil.setRelContabilidadeBanco(null);
            contaContabil.persiste();
        }

        for (ContaBancaria contaBancaria : getContasBancariasExcluidasDoRelacionamento()) {
            contaBancaria.setRelContabilidadeBanco(null);
            contaBancaria.persiste();
        }
    }

    public void exclui() {
        if (getRelacionamentosSelecionados().isEmpty()) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("EmptyRecordsToDelete"), "");
        } else {
            for (RelContabilidadeBanco itemSelecionado : getRelacionamentosSelecionados()) {

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

            if (getRelacionamentosSelecionados().size() == 1) {
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
        for (ContaContabil pItem : getListaDeContasContabeisSelecionadas()) {
            if (pItem.getRelContabilidadeBanco() == null) {
                if (getRelacionamento().getContaContabilCollection().contains(pItem)) {
                    JsfUtil.addErrorMessage("Erro!", "A conta já está selecionada!");
                } else {
                    getRelacionamento().getContaContabilCollection().add(pItem);
                    if (getContasContabeisExcluidasDoRelacionamento().contains(pItem)) {
                        getContasContabeisExcluidasDoRelacionamento().remove(pItem);
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
                if (getRelacionamento().getContaBancariaCollection().contains(pItem)) {
                    JsfUtil.addErrorMessage("Erro!", "A conta já está selecionada!");
                } else {
                    getRelacionamento().getContaBancariaCollection().add(pItem);
                    if (getContasBancariasExcluidasDoRelacionamento().contains(pItem)) {
                        getContasBancariasExcluidasDoRelacionamento().remove(pItem);
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
        for (ContaContabil pItem : getListaDeContasContabeisDoRelacionamentoSelecionadas()) {
            getRelacionamento().getContaContabilCollection().remove(pItem);
            if (!getContasContabeisExcluidasDoRelacionamento().contains(pItem)) {
                getContasContabeisExcluidasDoRelacionamento().add(pItem);
            }
            JsfUtil.addSuccessMessage("Conta removida com sucesso!", "Descrição: " + pItem.getDescricao());
        }
    }

    public void removeContasBancariasDoRelacionamentoSelecionadas() {
        for (ContaBancaria pItem : getListaDeContasBancariasDoRelacionamentoSelecionadas()) {
            getRelacionamento().getContaBancariaCollection().remove(pItem);
            if (!getContasBancariasExcluidasDoRelacionamento().contains(pItem)) {
                getContasBancariasExcluidasDoRelacionamento().add(pItem);
            }
            JsfUtil.addSuccessMessage("Conta removida com sucesso!", "Descrição: " + pItem.getDescricao());
        }
    }

    public void geraListaDeContasContabeis() {
        
        ContaContabil conta = new ContaContabil();       
        List<Order> ordem = new ArrayList<>();
        ordem.add(Order.asc("descricao"));      
        setListaDeContasContabeis(conta.obterLista(null, ordem));
        setListaDeContasContabeisSelecionadas(null);
    }

    public void geraListaDeContasBancarias() {
        
        ContaBancaria conta = new ContaBancaria();
  
        List<Order> ordem = new ArrayList<>();
        ordem.add(Order.asc("descricao"));                
               
        setListaDeContasBancarias(conta.obterLista(null, ordem));
        setListaDeContasBancariasSelecionadas(null);
    }
}
