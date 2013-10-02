/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diversos;

import controls.ContaBancaria;
import controls.ContaBancariaMovimento;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javax.servlet.http.Part;
import util.JsfUtil;

/**
 *
 * @author samuel
 */
public class ArquivoMovimentoContaBancaria {

    public void importaMovimentacao(ContaBancaria contaBancariaSelecionada, Part file) {

        List<String> linhasArquivo;
        linhasArquivo = importaLinhas(file);

        for (int i = 0; i < linhasArquivo.size() - 1; i++) {

            if (linhasArquivo.get(i).equals(contaBancariaSelecionada.getTagInicioMovimento())) {


                ContaBancariaMovimento movimento = new ContaBancariaMovimento();
                movimento.setConta(contaBancariaSelecionada);

                i++;

                while (!linhasArquivo.get(i).equals(contaBancariaSelecionada.getTagFimMovimento())) {

                    if (!linhasArquivo.get(i).equals(contaBancariaSelecionada.getTagFimMovimento())) {

                        if (linhasArquivo.get(i).indexOf(contaBancariaSelecionada.getTagData()) != -1) {

                            String dataMov = linhasArquivo.get(i).substring(contaBancariaSelecionada.getTagData().length());

                            try {
                                movimento.setDataMov(tagStringToDate(dataMov));
                            } catch (ParseException ex) {
                                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("Erro ao converter data!"));
                            }
                        }

                        if (linhasArquivo.get(i).indexOf(contaBancariaSelecionada.getTagValor()) != -1) {
                            movimento.setValor(Float.parseFloat(linhasArquivo.get(i).substring(contaBancariaSelecionada.getTagValor().length())));
                        }


                        if (linhasArquivo.get(i).indexOf(contaBancariaSelecionada.getTagNumDoc()) != -1) {
                            movimento.setNumdoc(linhasArquivo.get(i).substring(contaBancariaSelecionada.getTagNumDoc().length()));
                        }

                        if (linhasArquivo.get(i).indexOf(contaBancariaSelecionada.getTagHistorico()) != -1) {

                            String historico = linhasArquivo.get(i).substring(contaBancariaSelecionada.getTagHistorico().length());

                            if (historico.length() > 50) {
                                movimento.setHistorico(linhasArquivo.get(i).substring(contaBancariaSelecionada.getTagHistorico().length(), 50));

                            } else {
                                movimento.setHistorico(linhasArquivo.get(i).substring(contaBancariaSelecionada.getTagHistorico().length()));

                            }

                        }

                    }

                    i++;

                }




                movimento.persiste();










            }




        }

        JsfUtil.addSuccessMessage("Importação concluída");

    }

    private Date tagStringToDate(String dataMov) throws ParseException {

        DateFormat formatter;
        Date date;
        formatter = new SimpleDateFormat("yyyyMMdd");
        date = formatter.parse(dataMov);

        return date;


    }

    private List<String> importaLinhas(Part file) {

        List<String> lista = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(file.getInputStream());
            while (scanner.hasNext()) {
                lista.add(scanner.nextLine().trim());
            }

        } catch (IOException e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("Falha ao tentar ler arquivo!"));
        }

        return lista;
    }
}
