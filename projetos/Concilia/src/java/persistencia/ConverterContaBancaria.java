/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import controls.ContaBancaria;
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
@FacesConverter(value = "converterContaBancaria")
public class ConverterContaBancaria implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {

            ContaBancaria contaBancaria = new ContaBancaria();

            List<String> atributos = new ArrayList<>();
            atributos.add("idcontaBancaria");

            List<Object> valores = new ArrayList<>();
            valores.add(Integer.valueOf(value));

            return contaBancaria.obter(atributos, valores);            
            
            
            
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