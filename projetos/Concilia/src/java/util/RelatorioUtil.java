/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.sun.xml.ws.util.UtilException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author samuel
 */
public class RelatorioUtil {

    public static final int RELATORIO_PDF = 1;
    public static final int RELATORIO_EXCEL = 2;
    public static final int RELATORIO_HTML = 3;
    public static final int RELATORIO_PLANILHA_OPEN_OFFICE = 4;
    public static final int RELATORIO_TXT = 5;
    public static final int RELATORIO_CSV = 6;

    public StreamedContent geraRelatorio(HashMap parametrosRelatorio, String nomeRelatorioJasper,
            String nomeRelatorioSaida, int tipoRelatorio) throws UtilException {

        StreamedContent arquivoRetorno = null;

        try {

            Connection conexao = NewHibernateUtil.getSessionFactory().openStatelessSession().connection();
            FacesContext context = FacesContext.getCurrentInstance();
            String caminhoRelatorio = context.getExternalContext().getRealPath("pages" + File.separator + "relatorios");
            String caminhoArquivoJasper = caminhoRelatorio + File.separator + nomeRelatorioJasper + ".jasper";
            String caminhoArquivoRelatorio;
            JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivoJasper);
            JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio, conexao);
            JRExporter tipoArquivoExportado;
            String extensaoArquivoExportado;
            File arquivoGerado;
            switch (tipoRelatorio) {
                case RelatorioUtil.RELATORIO_PDF:
                    tipoArquivoExportado = new JRPdfExporter();
                    extensaoArquivoExportado = "pdf";
                    break;
                case RelatorioUtil.RELATORIO_HTML:
                    tipoArquivoExportado = new JRHtmlExporter();
                    extensaoArquivoExportado = "html";
                    break;
                case RelatorioUtil.RELATORIO_EXCEL:
                    tipoArquivoExportado = new JRXlsExporter();
                    extensaoArquivoExportado = "xls";
                    break;
                case RelatorioUtil.RELATORIO_PLANILHA_OPEN_OFFICE:
                    tipoArquivoExportado = new JROdsExporter();
                    extensaoArquivoExportado = "odt";
                    break;
                case RelatorioUtil.RELATORIO_TXT:
                    tipoArquivoExportado = new JRTextExporter();

                    tipoArquivoExportado.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, (float) 6.55);
                    tipoArquivoExportado.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, (float) 11.9);

                    extensaoArquivoExportado = "txt";
                    break;
                case RelatorioUtil.RELATORIO_CSV:
                    tipoArquivoExportado = new JRCsvExporter();
                    extensaoArquivoExportado = "csv";
                    break;
                default:
                    tipoArquivoExportado = new JRPdfExporter();
                    extensaoArquivoExportado = "pdf";
                    break;
            }
            caminhoArquivoRelatorio = caminhoRelatorio + File.separator + nomeRelatorioSaida + "." + extensaoArquivoExportado;
            arquivoGerado = new java.io.File(caminhoArquivoRelatorio);
            tipoArquivoExportado.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
            tipoArquivoExportado.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
            tipoArquivoExportado.exportReport();
            arquivoGerado.deleteOnExit();
            InputStream conteudoRelatorio = new FileInputStream(arquivoGerado);
            arquivoRetorno = new DefaultStreamedContent(conteudoRelatorio, "application/" + extensaoArquivoExportado, nomeRelatorioSaida + "." + extensaoArquivoExportado);
        } catch (JRException e) {
            throw new UtilException("Não foi possível gerar o relatório.", e);
        } catch (FileNotFoundException e) {
            throw new UtilException("Arquivo do relatório não encontrado.", e);
        }

        return arquivoRetorno;
    }

}
