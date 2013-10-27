package forms;

import controls.ContaContabil;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named("dialogSelecionaContaContabil")
@SessionScoped
public class DialogSelecionaContaContabil implements Serializable {

    private List<ContaContabil> lista;
    private List<ContaContabil> itensSelecionados;

    public DialogSelecionaContaContabil() {
    }

    public DialogSelecionaContaContabil(List<ContaContabil> lista, List<ContaContabil> itensSelecionados) {
        this.lista = lista;
        this.itensSelecionados = itensSelecionados;
    }

    public List<ContaContabil> getLista() {
        return lista;
    }

    public void setLista(List<ContaContabil> lista) {
        this.lista = lista;
    }

    public List<ContaContabil> getItensSelecionados() {
        return itensSelecionados;
    }

    public void setItensSelecionados(List<ContaContabil> itensSelecionados) {
        this.itensSelecionados = itensSelecionados;
    }

    public void abre() {
        RequestContext.getCurrentInstance().openDialog("selectContaContabil.xhtml");
    }

    public void selectFromDialog(ContaContabil conta) {
        RequestContext.getCurrentInstance().closeDialog(conta);
    }
    
    public void teste(){
        String teste = "teste";
    }
}
