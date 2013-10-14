package forms;

import controls.ContaContabil;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named("dialogSelecionaContaContabil")
@SessionScoped
public class DialogSelecionaContaContabil implements Serializable {

    private DataModel fListaDeContasContabeis;

    public DialogSelecionaContaContabil() {
    }

    public DialogSelecionaContaContabil(DataModel fListaDeContasContabeis) {
        this.fListaDeContasContabeis = fListaDeContasContabeis;
    }

    public void selectFromDialog(ContaContabil conta) {
        RequestContext.getCurrentInstance().closeDialog(conta);
    }

    public DataModel getListaDeContasContabeis() {
        return fListaDeContasContabeis;
    }

    public void setListaDeContasContabeis(DataModel listaDeContasContabeis) {
        this.fListaDeContasContabeis = listaDeContasContabeis;
    }

    public void abre() {
        RequestContext.getCurrentInstance().openDialog("contaContabil/selectContaContabil.xhtml");
    }
}
