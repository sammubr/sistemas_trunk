package persistencia;

import tabelas.ContabilidadeCategoria;
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
@FacesConverter(value = "converterContabilidadeCategoria")
public class ConverterContabilidadeCategoria implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {

            ContabilidadeCategoria item = new ContabilidadeCategoria();

            List<Criterion> filtro = new ArrayList<>();
            filtro.add(Restrictions.eq("idcontabilidadeCategoria", Integer.valueOf(value)));

            return item.obterObjeto(filtro, null);
            
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof ContabilidadeCategoria) {
            ContabilidadeCategoria item = (ContabilidadeCategoria) value;
            return String.valueOf(item.getIdcontabilidadeCategoria());
        }
        return "";
    }
}