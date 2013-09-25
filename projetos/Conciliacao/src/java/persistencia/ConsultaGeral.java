/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import util.NewHibernateUtil;

/**
 *
 * @author samuel
 */
public class ConsultaGeral {

    public static Collection<Persistencia> consultaTodos(Class classe, String ordem) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Collection<Persistencia> lista = null;
        try {
            tx = session.beginTransaction();
            Criteria crit = session.createCriteria(classe);

            if (!ordem.equals("")) {
                crit.addOrder(Order.asc(ordem));
            }

            lista = crit.list();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lista;

    }

    public static Collection consulta(Class classe, String atributo, Object valor, String ordem) {

        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Collection<Persistencia> lista = null;
        try {
            tx = session.beginTransaction();
            Criteria crit = session.createCriteria(classe);
            crit.add(Restrictions.eq(atributo, valor));
            if (!ordem.equals("")) {
                crit.addOrder(Order.asc(ordem));
            }
            lista = crit.list();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lista;
    }

    public static Object consulta(Class classe, List<String> atributos, List<String> valores) {

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
        } finally {
            session.close();
        }
        return objeto;
    }
}
