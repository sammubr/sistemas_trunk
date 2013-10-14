package forms;

import controls.ContaBancaria;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named("dialogSelecionaContaBancaria")
@SessionScoped
public class DialogSelecionaContaBancaria implements Serializable {

    private DataModel fListaDeContasBancarias;

    public DialogSelecionaContaBancaria() {
    }

    public DialogSelecionaContaBancaria(DataModel fListaDeContasBancarias) {
        this.fListaDeContasBancarias = fListaDeContasBancarias;
    }

    public void selectFromDialog(ContaBancaria conta) {
        RequestContext.getCurrentInstance().closeDialog(conta);
    }

    public DataModel getListaDeContasBancarias() {
        return fListaDeContasBancarias;
    }

    public void setListaDeContasBancarias(DataModel listaDeContasBancarias) {
        this.fListaDeContasBancarias = listaDeContasBancarias;
    }

    public void abre() {
        RequestContext.getCurrentInstance().openDialog("contaBancaria/selectContaBancaria.xhtml");
    }
    
}