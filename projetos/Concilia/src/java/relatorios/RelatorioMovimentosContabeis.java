package relatorios;

import com.sun.xml.ws.util.UtilException;
import tabelas.RelContabilidadeBanco;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.hibernate.criterion.Order;
import org.primefaces.model.StreamedContent;
import util.RelatorioUtil;

@Named("relatorioMovimentosContabeis")
@ViewScoped
public class RelatorioMovimentosContabeis implements Serializable {

    private StreamedContent arquivoRetorno;
    private int tipoRelatorio;
    private Date dataInicial;
    private Date dataFinal;
    private RelContabilidadeBanco relacionamento;
    private List<RelContabilidadeBanco> listaDeRelacionamentos;
    
    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }    
    
    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
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

    public void setArquivoRetorno(StreamedContent arquivoRetorno) {
        this.arquivoRetorno = arquivoRetorno;
    }

    public int getTipoRelatorio() {
        return tipoRelatorio;
    }

    public void setTipoRelatorio(int tipoRelatorio) {
        this.tipoRelatorio = tipoRelatorio;
    }

    public RelatorioMovimentosContabeis() {
        geraListaDeRelacionamentos();
    }

    private void geraListaDeRelacionamentos() {
        RelContabilidadeBanco consulta = new RelContabilidadeBanco();
        List<Order> ordem = new ArrayList<>();
        ordem.add(Order.asc("descricao"));
        setListaDeRelacionamentos(consulta.obterLista(null, ordem));
    }
    
    public StreamedContent getArquivoRetorno() throws ParseException {

        FacesContext context = FacesContext.getCurrentInstance();
        String nomeRelatorioJasper = "MovimentosContabeis";
        String nomeRelatorioSaida = "MovimentosContabeis";
        RelatorioUtil relatorioUtil = new RelatorioUtil();
        HashMap parametrosRelatorio = new HashMap();
        parametrosRelatorio.put("numeroRelacionamento", relacionamento.getId());
        parametrosRelatorio.put("dataInicial", dataInicial);
        parametrosRelatorio.put("dataFinal", dataFinal);

        try {
            this.arquivoRetorno = relatorioUtil.geraRelatorio(parametrosRelatorio, nomeRelatorioJasper, nomeRelatorioSaida, tipoRelatorio);
        } catch (UtilException e) {
            context.addMessage(null, new FacesMessage(e.getMessage()));
            return null;
        }
        return this.arquivoRetorno;
    }



}
