/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import controls.ContaBancaria;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import util.NewHibernateUtil;

/**
 *
 * @author samuel
 */
@FacesConverter(value = "converterConta")
public class ConverterConta implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            Criteria crit = NewHibernateUtil.getSessionFactory().openSession().createCriteria(ContaBancaria.class);
            crit.add(Restrictions.eq("idcontaBancaria", Integer.valueOf(value)));
            return crit.list().get(0);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof ContaBancaria) {
            ContaBancaria contaBancaria = (ContaBancaria) value;
            return String.valueOf(contaBancaria.getIdcontaBancaria());
        }
        return "";
    }
}