package persistencia;

import tabelas.BancoCategoria;
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
@FacesConverter(value = "converterBancoCategoria")
public class ConverterBancoCategoria implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {

            BancoCategoria item = new BancoCategoria();

            List<Criterion> filtro = new ArrayList<>();
            filtro.add(Restrictions.eq("idbancoCategoria", Integer.valueOf(value)));

            return item.obterObjeto(filtro, null);
            
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof BancoCategoria) {
            BancoCategoria item = (BancoCategoria) value;
            return String.valueOf(item.getIdbancoCategoria());
        }
        return "";
    }
}