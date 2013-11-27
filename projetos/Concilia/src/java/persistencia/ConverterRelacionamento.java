/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import controls.RelContabilidadeBanco;
import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author samuel
 */
@FacesConverter(value = "converterRelacionamento")
public class ConverterRelacionamento implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {

            RelContabilidadeBanco relacionamento = new RelContabilidadeBanco();

            List<String> atributos = new ArrayList<>();
            atributos.add("id");

            List<Object> valores = new ArrayList<>();
            valores.add(Integer.valueOf(value));

            return relacionamento.obter(atributos, valores);

        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof RelContabilidadeBanco) {
            RelContabilidadeBanco relacionamento = (RelContabilidadeBanco) value;
            return String.valueOf(relacionamento.getId());
        }
        return "";
    }
}
