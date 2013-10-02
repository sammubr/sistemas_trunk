/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import controls.ContaContabil;
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
@FacesConverter(value = "converterContaContabil")
public class ConverterContaContabil implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {            
            
            ContaContabil contaContabil = new ContaContabil();

            List<String> atributos = new ArrayList<>();
            atributos.add("idcontaContabil");

            List<Object> valores = new ArrayList<>();
            valores.add(Integer.valueOf(value));

            return contaContabil.obter(atributos, valores);            
            
            
            
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof ContaContabil) {
            ContaContabil contaBancaria = (ContaContabil) value;
            return String.valueOf(contaBancaria.getIdcontaContabil());
        }
        return "";
    }
}