/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import controls.Banco;
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
@FacesConverter(value = "converterBanco")
public class ConverterBanco implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            
            
            Criteria crit = NewHibernateUtil.getSessionFactory().openSession().createCriteria(Banco.class);
            crit.add(Restrictions.eq("idbanco", Integer.valueOf(value)));
            return crit.list().get(0);
            
        }
        return null;
    }
    
    

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Banco) {
            Banco banco = (Banco) value;
            return String.valueOf(banco.getIdbanco());
        }
        return "";
    }
    
    
    
    
    
}
 