/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diversos;

import controls.ContaContabil;
import controls.ContaContabilMovimento;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.primefaces.model.UploadedFile;
import util.JsfUtil;

/**
 *
 * @author samuel
 */
public class ArquivoMovimentoContaContabil {

    public void importaMovimentacao(ContaContabil contaContabilSelecionada, UploadedFile file) throws Exception {

        List<String> linhasArquivo;
        linhasArquivo = importaLinhas(file);
        ContaContabilMovimento movimento = new ContaContabilMovimento();
        Integer contador = 0;

        for (int i = 0; i < linhasArquivo.size() - 1; i++) {

            if (linhasArquivo.get(i).equals(contaContabilSelecionada.getTagInicioMovimento())) {
                movimento = new ContaContabilMovimento();
                movimento.setConta(contaContabilSelecionada);
            }

            if (linhasArquivo.get(i).equals(contaContabilSelecionada.getTagFimMovimento())) {
                movimento.persiste();
                contador++;
            }

            if (linhasArquivo.get(i).indexOf(contaContabilSelecionada.getTagData()) != -1) {
                String dataMov = linhasArquivo.get(i).substring(contaContabilSelecionada.getTagData().length());
                movimento.setDataMov(tagStringToDate(dataMov));
            }

            if (linhasArquivo.get(i).indexOf(contaContabilSelecionada.getTagValor()) != -1) {
                movimento.setValor(new BigDecimal(linhasArquivo.get(i).substring(contaContabilSelecionada.getTagValor().length())));
            }


            if (linhasArquivo.get(i).indexOf(contaContabilSelecionada.getTagNumDoc()) != -1) {
                movimento.setNumdoc(linhasArquivo.get(i).substring(contaContabilSelecionada.getTagNumDoc().length()));
            }

            if (linhasArquivo.get(i).indexOf(contaContabilSelecionada.getTagHistorico()) != -1) {
                String historico = linhasArquivo.get(i).substring(contaContabilSelecionada.getTagHistorico().length());
                if (historico.length() > 50) {
                    movimento.setHistorico(linhasArquivo.get(i).substring(contaContabilSelecionada.getTagHistorico().length(), 50));
                } else {
                    movimento.setHistorico(linhasArquivo.get(i).substring(contaContabilSelecionada.getTagHistorico().length()));
                }
            }
        }

        if (contador == 0) {
            JsfUtil.addErrorMessage("Nenhum movimento importado!", "Confira o layout do arquivo e as tags informadas.");
        } else {
            JsfUtil.addSuccessMessage("Importação concluída!", contador + " movimentos(s) importado(s).");
        }
    }

    private Date tagStringToDate(String dataMov) throws ParseException {

        DateFormat formatter;
        Date date;
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        date = formatter.parse(dataMov);

        return date;


    }

    private List<String> importaLinhas(UploadedFile file) throws IOException {

        List<String> lista = new ArrayList<>();

            Scanner scanner = new Scanner(file.getInputstream());
            while (scanner.hasNext()) {
                String linha = new String(scanner.nextLine().trim().getBytes("ISO-8859-1"));
                lista.add(linha);
            }

        return lista;
    }
}
