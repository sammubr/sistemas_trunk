package util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class JsfUtil {

    public static void addErrorMessage(String titulo, Exception excessao) {
        addErrorMessage(titulo, excessao.getLocalizedMessage());
    }

    public static void addErrorMessage(String titulo, String mensagem) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, mensagem);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addSuccessMessage(String titulo, String mensagem) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensagem);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JsfUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }
}