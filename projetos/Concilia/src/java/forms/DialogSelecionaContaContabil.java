package forms;

import controls.ContaContabil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.CollectionDataModel;
import javax.faces.model.DataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named("dialogSelecionaContaContabil")
@ViewScoped
public class DialogSelecionaContaContabil implements Serializable {

    private DataModel fListaDeContasContabeis;

    public void selectFromDialog(ContaContabil conta) {
        RequestContext.getCurrentInstance().closeDialog(conta);
    }

    public DataModel getListaDeContasContabeis() {
        geraListaDeContasContabeis();
        return fListaDeContasContabeis;
    }

    public void setListaDeContasContabeis(DataModel listaDeContasContabeis) {
        this.fListaDeContasContabeis = listaDeContasContabeis;
    }

    private void geraListaDeContasContabeis() {
        List<String> ordem = new ArrayList<>();
        ordem.add("descricao");
        ContaContabil contaContabil = new ContaContabil();
        this.fListaDeContasContabeis = new CollectionDataModel(contaContabil.obter(null, null, ordem));
    }
}
