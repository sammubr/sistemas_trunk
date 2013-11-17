
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.model.UploadedFile;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author samuel
 */
public class Arquivo {

    private String dataAnterior = "";
    private String nomeArquivo = "";
    private InputStream arquivoAlterado;
    private List<String> arquivoOriginal;
    private BigDecimal saldoFinal;

    private List<String> leArquivo(UploadedFile file) throws IOException {

        nomeArquivo = file.getFileName();

        List<String> lista = new ArrayList<>();

        Scanner scanner = new Scanner(file.getInputstream());
        while (scanner.hasNext()) {


            String linha = scanner.nextLine().trim();

            if (linha.length() == 43
                    && linha.substring(0, 16).equals("Saldo Anterior :")) {
                String valor;

                if (linha.substring(42, 43).trim().equals("-")) {
                    valor = linha.substring(42, 43).trim() + linha.substring(27, 39).trim().replace(".", "") + "." + linha.substring(40, 42).trim();
                } else {
                    valor = linha.substring(27, 39).trim().replace(".", "") + "." + linha.substring(40, 42).trim();
                }


                saldoFinal = new BigDecimal(valor);
            }

            if (linha.length() == 132
                    && isInt(linha.substring(0, 6).trim())) {
                lista.add(linha);
            }


        }

        return lista;
    }

    private boolean isInt(String v) {
        try {
            Integer.parseInt(v);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String pegaHistorico(String linha) {

        String historico = "<MEMO>";
        historico += linha.substring(87, 111).trim();
        historico += "\n";
        return historico;


    }

    private String pegaValor(String linha) {



        String historico = "<TRNAMT>";

        String valor;

        if (linha.substring(131, 132).trim().equals("-")) {
            valor = linha.substring(131, 132).trim() + linha.substring(117, 128).trim().replace(".", "") + "." + linha.substring(129, 131).trim();
        } else {
            valor = linha.substring(117, 128).trim().replace(".", "") + "." + linha.substring(129, 131).trim();
        }
        
        
        historico += valor;
        
        
        BigDecimal valor2 = new BigDecimal(valor);

        saldoFinal = saldoFinal.add(valor2);

        historico += "\n";
        return historico;


    }

    private String pegaData(String linha) {

        String historico = "<DTPOSTED>";

        if (linha.substring(7, 17).equals("          ")) {
            historico += dataAnterior;
        } else {
            dataAnterior = linha.substring(7, 17);
            historico += dataAnterior;
        }

        historico += "\n";
        return historico;


    }

    private String pegaNumDoc(String linha) {

        String historico = "<CHECKNUM>";
        historico += linha.substring(0, 6).trim();
        historico += "\n";
        return historico;


    }

    void converte(UploadedFile file) throws IOException {


        saldoFinal = new BigDecimal(0.00);


        String linhasArquivoNovo = "";
        arquivoOriginal = leArquivo(file);

        if (arquivoOriginal.size() > 0) {

            for (int i = 0; i < arquivoOriginal.size(); i++) {
                linhasArquivoNovo += "<STMTTRN>";
                linhasArquivoNovo += "\n";
                linhasArquivoNovo += pegaHistorico(arquivoOriginal.get(i));
                linhasArquivoNovo += pegaValor(arquivoOriginal.get(i));
                linhasArquivoNovo += pegaData(arquivoOriginal.get(i));
                linhasArquivoNovo += pegaNumDoc(arquivoOriginal.get(i));
                linhasArquivoNovo += "</STMTTRN>";
                linhasArquivoNovo += "\n";
            }

            linhasArquivoNovo += "<SALDO_FINAL>" + saldoFinal;

            arquivoAlterado = new ByteArrayInputStream(linhasArquivoNovo.getBytes("UTF-8"));
            salvaArquivoNovo();
        }
    }

    public void salvaArquivoNovo() throws IOException {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
        response.reset();
        response.setContentType("application/txt");
        response.setHeader("Content-disposition", "attachment; filename=\"" + nomeArquivo + "\"");
        BufferedInputStream input = null;
        BufferedOutputStream output = null;

        try {
            input = new BufferedInputStream(arquivoAlterado);
            output = new BufferedOutputStream(response.getOutputStream());

            byte[] buffer = new byte[10240];
            for (int length; (length = input.read(buffer)) > 0;) {
                output.write(buffer, 0, length);
            }
        } finally {
            output.close();
            input.close();
        }

        facesContext.responseComplete();
    }
}
