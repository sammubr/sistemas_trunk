/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import controls.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistencia.ConsultaGeral;

/**
 *
 * @author samuel
 */
public class Login extends javax.servlet.http.HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<String> atributos = new ArrayList<>();
        atributos.add("login");
        atributos.add("senha");
        List<Object> valores = new ArrayList<>();
        valores.add(request.getParameter("login"));
        valores.add(request.getParameter("senha"));
        
        Usuario usuario = new Usuario();
        
        usuario = (Usuario) usuario.obter(atributos, valores);
        

        //Usuario usuario = (Usuario) ConsultaGeral.consulta(Usuario.class, atributos, valores);

        if (usuario == null) {
            request.getSession().setAttribute("msg", "Login ou senha incorretos!");
            response.sendRedirect("login.xhtml");
        } else {
            request.getSession().setAttribute("usuario", usuario);
            request.getSession().setAttribute("msg", "Você está logado no sistema!");
            response.sendRedirect("pages/index.xhtml");
        }

    }
}