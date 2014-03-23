/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import tabelas.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FiltroSeguranca implements Filter {

    private static List<String> allowedURIs;

    @Override
    public void init(FilterConfig config) throws ServletException {
        if (allowedURIs == null) {
            allowedURIs = new ArrayList<>();
            allowedURIs.add(config.getInitParameter("loginActionURI"));
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = ((HttpServletRequest) req).getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null && !allowedURIs.contains(req.getRequestURI())) {
            session.setAttribute("msg", "Você não está logado no sistema!");
            RequestDispatcher rd = req.getRequestDispatcher("/login.xhtml");
            rd.forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}