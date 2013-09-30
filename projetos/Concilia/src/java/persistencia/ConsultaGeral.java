/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import util.JsfUtil;
import util.NewHibernateUtil;

/**
 *
 * @author samuel
 */
public class ConsultaGeral {

    public static Collection<Persistencia> consultaTodos2(Class classe, List<String> atributos, List<Object> valores, List<String> ordem) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Collection<Persistencia> lista = null;
        try {
            tx = session.beginTransaction();
            Criteria crit = session.createCriteria(classe);

            if (atributos != null) {
                for (int i = 0; i < atributos.size(); i++) {
                    crit.add(Restrictions.eq(atributos.get(i), valores.get(i)));
                }
            }
            if (ordem != null) {
                for (int i = 0; i < ordem.size(); i++) {
                    if (!ordem.get(i).equals("")) {
                        crit.addOrder(Order.asc(ordem.get(i)));
                    }
                }
            }
            lista = crit.list();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("ConsultaErrorOccured"));
        } finally {
            session.close();
        }
        return lista;

    }

    public static Object consulta2(Class classe, List<String> atributos, List<Object> valores) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Object objeto = null;
        try {
            tx = session.beginTransaction();
            Criteria crit = session.createCriteria(classe);
            for (int i = 0; i < atributos.size(); i++) {
                crit.add(Restrictions.eq(atributos.get(i), valores.get(i)));
            }
            if (crit.list().size() > 0) {
                objeto = crit.list().get(0);
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("ConsultaErrorOccured"));
        } finally {
            session.close();
        }
        return objeto;
    }
    
    
    
    //-----------------------------------TESTAR!!!!!!!!!!!!!!
    
    
    protected static Collection<Persistencia> consulta2(String consulta) {
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

    protected Collection<Persistencia> consultaTodos2() {
        return consulta2("FROM " + this.getClass().getName() + " as u");
    }

    protected Collection<Persistencia> consultaTodos2(String ordem) {
        return consulta2("FROM " + this.getClass().getName() + " as u order by u." + ordem);
    }

    protected int registrosCom() {
        return count2("select from " + this.getClass().getName() + " as u");
    }

    protected Collection<Persistencia> registrosCom2(String campo, String conteudo) {
        return consulta2("from " + this.getClass().getName() + " as u where u." + campo + " = " + conteudo);
    }

    protected Collection<Persistencia> registrosCom2(String campo, Integer conteudo) {
        return consulta2("from " + this.getClass().getName() + " as u where u." + campo + " = " + conteudo);
    }

    protected int count2(String consulta) {
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

    protected List<Object> findRange2(int[] range) {
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
