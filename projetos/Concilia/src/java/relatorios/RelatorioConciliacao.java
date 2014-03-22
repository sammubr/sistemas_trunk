/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;

import com.sun.xml.ws.util.UtilException;
import controls.RelContabilidadeBanco;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

/**
 *
 * @author samuel
 */
@Named("relatorioConciliacao")
@ViewScoped
public class RelatorioConciliacao implements Serializable {

    private StreamedContent arquivoRetorno;
    private int tipoRelatorio;
    private Date dataConciliacao;
    private RelContabilidadeBanco relacionamento;
    private List<RelContabilidadeBanco> listaDeRelacionamentos;
    
    public Date getDataConciliacao() {
        return dataConciliacao;
    }

    public void setDataConciliacao(Date dataConciliacao) {
        this.dataConciliacao = dataConciliacao;
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

    public RelatorioConciliacao() {
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
        String nomeRelatorioJasper = "Conciliacao";
        String nomeRelatorioSaida = "Conciliacao";
        RelatorioUtil relatorioUtil = new RelatorioUtil();
        HashMap parametrosRelatorio = new HashMap();
        parametrosRelatorio.put("numeroRelacionamento", relacionamento.getId());
        parametrosRelatorio.put("dataConciliacao", dataConciliacao);

        try {
            this.arquivoRetorno = relatorioUtil.geraRelatorio(parametrosRelatorio, nomeRelatorioJasper, nomeRelatorioSaida, tipoRelatorio);
        } catch (UtilException e) {
            context.addMessage(null, new FacesMessage(e.getMessage()));
            return null;
        }
        return this.arquivoRetorno;
    }



}
