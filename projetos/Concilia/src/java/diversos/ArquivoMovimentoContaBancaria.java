/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diversos;

import tabelas.ContaBancaria;
import tabelas.ContaBancariaMovimento;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import org.primefaces.model.UploadedFile;
import util.JsfUtil;

/**
 *
 * @author samuel
 */
public class ArquivoMovimentoContaBancaria {

    public void importaMovimentacao(ContaBancaria contaBancariaSelecionada, UploadedFile file) throws Exception {

        List<String> linhasArquivo;
        linhasArquivo = importaLinhas(file);
        ContaBancariaMovimento movimento = new ContaBancariaMovimento();
        Integer contador = 0;

        for (int i = 0; i < linhasArquivo.size() - 1; i++) {

            if (linhasArquivo.get(i).equals(contaBancariaSelecionada.getBanco().getTagInicioMovimento())) {
                movimento = new ContaBancariaMovimento();
                movimento.setConta(contaBancariaSelecionada);
            }

            if (linhasArquivo.get(i).equals(contaBancariaSelecionada.getBanco().getTagFimMovimento())) {
                movimento.persiste();
                contador++;
            }

            if (linhasArquivo.get(i).contains(contaBancariaSelecionada.getBanco().getTagData())) {
                String dataMov = linhasArquivo.get(i).substring(contaBancariaSelecionada.getBanco().getTagData().length());
                movimento.setDataMov(tagStringToDate(dataMov));
            }

            if (linhasArquivo.get(i).contains(contaBancariaSelecionada.getBanco().getTagValor())) {
                movimento.setValor(new BigDecimal(linhasArquivo.get(i).substring(contaBancariaSelecionada.getBanco().getTagValor().length())));
            }


            if (linhasArquivo.get(i).contains(contaBancariaSelecionada.getBanco().getTagNumDoc())) {
                movimento.setNumdoc(linhasArquivo.get(i).substring(contaBancariaSelecionada.getBanco().getTagNumDoc().length()));
            }

            if (linhasArquivo.get(i).contains(contaBancariaSelecionada.getBanco().getTagHistorico())) {
                String historico = linhasArquivo.get(i).substring(contaBancariaSelecionada.getBanco().getTagHistorico().length());
                if (historico.length() > 50) {
                    movimento.setHistorico(linhasArquivo.get(i).substring(contaBancariaSelecionada.getBanco().getTagHistorico().length(), 50));
                } else {
                    movimento.setHistorico(linhasArquivo.get(i).substring(contaBancariaSelecionada.getBanco().getTagHistorico().length()));
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
        formatter = new SimpleDateFormat("yyyyMMdd");
        date = formatter.parse(dataMov);

        return date;

    }

    private List<String> importaLinhas(UploadedFile file) {

        List<String> lista = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(file.getInputstream());
            while (scanner.hasNext()) {
                String linha = new String(scanner.nextLine().trim().getBytes("ISO-8859-1"));
                //lista.add(scanner.nextLine().trim());
                lista.add(linha);
            }

        } catch (IOException e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("LeituraArquivoErro"), e);
        }

        return lista;
    }
}