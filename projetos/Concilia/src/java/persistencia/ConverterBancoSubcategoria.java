package persistencia;

import tabelas.BancoSubcategoria;
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
@FacesConverter(value = "converterBancoSubcategoria")
public class ConverterBancoSubcategoria implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {

            BancoSubcategoria item = new BancoSubcategoria();

            List<Criterion> filtro = new ArrayList<>();
            filtro.add(Restrictions.eq("idbancoSubcategoria", Integer.valueOf(value)));

            return item.obterObjeto(filtro, null);
            
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof BancoSubcategoria) {
            BancoSubcategoria item = (BancoSubcategoria) value;
            return String.valueOf(item.getIdbancoSubcategoria());
        }
        return "";
    }
}