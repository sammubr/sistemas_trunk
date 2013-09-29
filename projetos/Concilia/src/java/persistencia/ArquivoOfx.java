/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author samuel
 */
class ArquivoOfx {

    ArquivoOfx(InputStream inputstream) throws IOException {
        try {
            InputStreamReader streamReader = new InputStreamReader(inputstream);
            try (BufferedReader arquivo = new BufferedReader(streamReader)) {
                System.out.printf("%s\n", arquivo.readLine());
                while (arquivo.readLine() != null) {
                    System.out.printf("%s\n", arquivo.readLine());
                }
            }
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }



    }
}
