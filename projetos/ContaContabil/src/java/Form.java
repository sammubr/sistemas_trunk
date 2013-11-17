/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author samuel
 */
@ManagedBean
@SessionScoped
public class Form {

    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {
        if (!file.getFileName().isEmpty()) {
            try {
                Arquivo arquivo = new Arquivo();
                arquivo.converte(file);
                FacesMessage msg = new FacesMessage("Arquivo" + file.getFileName() + " convertido com sucesso!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (IOException ex) {
                Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {            
                FacesMessage msg = new FacesMessage("Selecione um arquivo!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
