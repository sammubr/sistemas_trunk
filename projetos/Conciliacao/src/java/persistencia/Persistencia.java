/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.JsfUtil;
import util.NewHibernateUtil;

/**
 *
 * @author samuel
 */
public class Persistencia {

    public boolean persiste() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(this);
            session.flush();
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return false;
        } finally {
            session.close();
        }

    }

    public boolean exclui() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(this);
            session.flush();
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return false;
        } finally {
            session.close();
        }
    }
    
    
    //TODO: Incluir na classe ConsultaGeral a parte de baixo!
    
    

    private static Collection<Persistencia> consulta(String consulta) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Collection<Persistencia> lista = null;
        try {
            Query q;
            tx = session.beginTransaction();
            q = session.createQuery(consulta);
            lista = q.list();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lista;
    }

    protected Collection<Persistencia> consultaTodos() {
        return consulta("FROM " + this.getClass().getName() + " as u");
    }

    public Collection<Persistencia> consultaTodos(String ordem) {
        return consulta("FROM " + this.getClass().getName() + " as u order by u." + ordem);
    }

    protected int registrosCom() {
        return count("select from " + this.getClass().getName() + " as u");
    }

    public Collection<Persistencia> registrosCom(String campo, String conteudo) {
        return consulta("from " + this.getClass().getName() + " as u where u." + campo + " = " + conteudo);
    }

    public Collection<Persistencia> registrosCom(String campo, Integer conteudo) {
        return consulta("from " + this.getClass().getName() + " as u where u." + campo + " = " + conteudo);
    }

    private int count(String consulta) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        int lista = 0;
        try {
            Query q;
            tx = session.beginTransaction();
            q = session.createQuery(consulta);
            lista = q.list().size();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lista;
    }

    protected List<Object> findRange(int[] range) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Object> lista = null;
        try {
            Query q;
            tx = session.beginTransaction();
            String consulta = "FROM " + this.getClass();
            q = session.createQuery(consulta);
            q.setMaxResults(range[1] - range[0]);
            q.setFirstResult(range[0]);
            lista = q.list();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lista;
    }

}
