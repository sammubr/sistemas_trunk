/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author samuel
 */
@ManagedBean
@SessionScoped
public class Contato {

    private String nome;
    private String email;
    private String texto;

    public Contato() {
        String teste = "teste";
    }

    public Contato(String nome, String email, String texto) {
        this.nome = nome;
        this.email = email;
        this.texto = texto;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String enviaEmail() {
        FacesMessage msg = new FacesMessage("E-mail enviado com sucesso!");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "conteudo.xhtml";
    }
}
