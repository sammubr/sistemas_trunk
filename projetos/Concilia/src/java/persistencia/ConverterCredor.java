package persistencia;

import tabelas.Credor;
import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author samuel
 */
@FacesConverter(value = "converterCredor")
public class ConverterCredor implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {

            Credor item = new Credor();

            List<Criterion> filtro = new ArrayList<>();
            filtro.add(Restrictions.eq("idcredor", Integer.valueOf(value)));

            return item.obterObjeto(filtro, null);
            
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Credor) {
            Credor item = (Credor) value;
            return String.valueOf(item.getIdcredor());
        }
        return "";
    }
}
