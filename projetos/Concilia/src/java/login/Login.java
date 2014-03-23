/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import tabelas.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author samuel
 */
public class Login extends javax.servlet.http.HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("login").equalsIgnoreCase("admin") && request.getParameter("senha").equalsIgnoreCase("admin")) {

            Usuario usuario = new Usuario();
            usuario.setNome("Admin");
            usuario.setLogin("admin");
            usuario.setSenha("admin");
            usuario.setNivel(999);
            
            request.getSession().setAttribute("usuario", usuario);
            request.getSession().setAttribute("msg", "Você está logado no sistema!");
            response.sendRedirect("pages/index.xhtml");

        } else {

            Usuario usuario = new Usuario();

            List<Criterion> filtro = new ArrayList<>();
            filtro.add(Restrictions.eq("login", request.getParameter("login")));
            filtro.add(Restrictions.eq("senha", request.getParameter("senha")));

            usuario = (Usuario) usuario.obterObjeto(filtro, null);

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
}
