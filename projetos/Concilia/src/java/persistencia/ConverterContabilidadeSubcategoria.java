package persistencia;

import tabelas.ContabilidadeSubcategoria;
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
@FacesConverter(value = "converterContabilidadeSubcategoria")
public class ConverterContabilidadeSubcategoria implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {

            ContabilidadeSubcategoria item = new ContabilidadeSubcategoria();

            List<Criterion> filtro = new ArrayList<>();
            filtro.add(Restrictions.eq("idcontabilidadeSubcategoria", Integer.valueOf(value)));

            return item.obterObjeto(filtro, null);
            
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof ContabilidadeSubcategoria) {
            ContabilidadeSubcategoria item = (ContabilidadeSubcategoria) value;
            return String.valueOf(item.getIdcontabilidadeSubcategoria());
        }
        return "";
    }
}