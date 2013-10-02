/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diversos;

import controls.ContaContabil;
import controls.ContaContabilMovimento;
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
public class ArquivoMovimentoContaContabil {

    public void importaMovimentacao(ContaContabil contaContabilSelecionada, Part file) {

        List<String> linhasArquivo;
        linhasArquivo = importaLinhas(file);

        for (int i = 0; i < linhasArquivo.size() - 1; i++) {

            if (linhasArquivo.get(i).equals(contaContabilSelecionada.getTagInicioMovimento())) {


                ContaContabilMovimento movimento = new ContaContabilMovimento();
                movimento.setConta(contaContabilSelecionada);

                i++;

                while (!linhasArquivo.get(i).equals(contaContabilSelecionada.getTagFimMovimento())) {

                    if (!linhasArquivo.get(i).equals(contaContabilSelecionada.getTagFimMovimento())) {

                        if (linhasArquivo.get(i).indexOf(contaContabilSelecionada.getTagData()) != -1) {

                            String dataMov = linhasArquivo.get(i).substring(contaContabilSelecionada.getTagData().length());

                            try {
                                movimento.setDataMov(tagStringToDate(dataMov));
                            } catch (ParseException ex) {
                                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("Erro ao converter data!"));
                            }
                        }

                        if (linhasArquivo.get(i).indexOf(contaContabilSelecionada.getTagValor()) != -1) {
                            movimento.setValor(Float.parseFloat(linhasArquivo.get(i).substring(contaContabilSelecionada.getTagValor().length())));
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
