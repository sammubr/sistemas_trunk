/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javax.servlet.http.Part;
import util.JsfUtil;

/**
 *
 * @author samuel
 */
public class ArquivoDeMovimento {

    void importaMovimentacao(Part file) {

        List<String> lista = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(file.getInputStream());
            while (scanner.hasNext()) {
                lista.add(scanner.nextLine().trim());
            }
        } catch (IOException e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("Falha ao tentar ler arquivo!"));
        }
        
        for (int i = 0; i < lista.size()-1; i++) {
            
            
            
            
            
            
            
        }
        
        
        
        
        
        
        
    }
}
