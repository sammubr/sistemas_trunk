/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import controls.Banco;
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
@FacesConverter(value = "converterBanco")
public class ConverterBanco implements Converter {
    

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {

            Banco banco = new Banco();

            List<String> atributos = new ArrayList<>();
            atributos.add("idbanco");

            List<Object> valores = new ArrayList<>();
            valores.add(Integer.valueOf(value));

            return banco.obter(atributos, valores);


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
