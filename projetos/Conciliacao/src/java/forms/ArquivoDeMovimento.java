/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.servlet.http.Part;

/**
 *
 * @author samuel
 */
public class ArquivoDeMovimento {

    List<String> lista = new ArrayList<>();
//samuel
    ArquivoDeMovimento(Part file) {
        try {
            Scanner scanner = new Scanner(file.getInputStream());
            while (scanner.hasNext()) {
                lista.add(scanner.nextLine().trim());
            }
        } catch (IOException e) {
            System.out.println("Falha ao tentar ler arquivo! " + e.getMessage());
        }


    }
}
