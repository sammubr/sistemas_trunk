/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import tabelas.ContaBancaria;
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
@FacesConverter(value = "converterContaBancaria")
public class ConverterContaBancaria implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {

            ContaBancaria contaBancaria = new ContaBancaria();
            
            List<Criterion> filtro = new ArrayList<>();
            filtro.add(Restrictions.eq("idcontaBancaria", Integer.valueOf(value)));            

            return contaBancaria.obterObjeto(filtro, null);                                    
            
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