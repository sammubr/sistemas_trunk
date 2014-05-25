/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import java.util.ResourceBundle;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import util.JsfUtil;
import util.NewHibernateUtil;

/**
 *
 * @author samuel
 */
public class Persistencia {

    public void persiste() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(this);
            session.flush();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"), e);
//            throw e;
        } finally {
            session.close();
        }
    }

    public void exclui() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(this);
            session.flush();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"), e);
//            throw e;
        } finally {
            session.close();
        }
    }

    public List obterLista(List<Criterion> criterios, List<Order> ordenamento) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Persistencia> lista = null;
        try {
            tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.getClass());

            if (criterios != null) {
                for (Criterion criterio : criterios) {
                    crit.add(criterio);
                }
            }

            if (ordenamento != null) {
                for (Order ordem : ordenamento) {
                    crit.addOrder(ordem);
                }
            }

            lista = crit.list();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("ConsultaErrorOccured"), e);
//            throw e;
        } finally {
            session.close();
        }
        return lista;
    }

    public Object obterObjeto(List<Criterion> criterios, List<Order> ordenamento) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Object objeto = null;
        try {
            tx = session.beginTransaction();
            Criteria crit = session.createCriteria(this.getClass());

            if (criterios != null) {
                for (Criterion criterio : criterios) {
                    crit.add(criterio);
                }
            }

            if (ordenamento != null) {
                for (Order ordem : ordenamento) {
                    crit.addOrder(ordem);
                }
            }

            if (crit.list().size() > 0) {
                objeto = crit.list().get(0);
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("ConsultaErrorOccured"), e);
//            throw e;
        } finally {
            session.close();
        }
        return objeto;
    }
}
