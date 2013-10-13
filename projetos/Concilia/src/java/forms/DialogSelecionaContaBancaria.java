package forms;

import controls.ContaBancaria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.CollectionDataModel;
import javax.faces.model.DataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named("dialogSelecionaContaBancaria")
@ViewScoped
public class DialogSelecionaContaBancaria implements Serializable {

    private DataModel fListaDeContasBancarias;

    public void selectFromDialog(ContaBancaria conta) {
        RequestContext.getCurrentInstance().closeDialog(conta);
    }

    public DataModel getListaDeContasBancarias() {
        geraListaDeContasBancarias();
        return fListaDeContasBancarias;
    }

    public void setListaDeContasBancarias(DataModel listaDeContasBancarias) {
        this.fListaDeContasBancarias = listaDeContasBancarias;
    }

    private void geraListaDeContasBancarias() {
        List<String> ordem = new ArrayList<>();
        ordem.add("descricao");
        ContaBancaria conta = new ContaBancaria();
        this.fListaDeContasBancarias = new CollectionDataModel(conta.obter(null, null, ordem));
    }
}