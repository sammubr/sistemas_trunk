/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import org.hibernate.Criteria;
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
            if (tx != null) {
                tx.rollback();
            }
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
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return false;
        } finally {
            session.close();
        }
    }

    public Collection<Persistencia> obter(List<String> atributos, List<Object> valores, List<String> ordem) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Collection<Persistencia> lista = null;
        try {
            tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.getClass());
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

    public Object obter(List<String> atributos, List<Object> valores) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Object objeto = null;
        try {
            tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.getClass());
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
        return (Persistencia) objeto;
    }
}