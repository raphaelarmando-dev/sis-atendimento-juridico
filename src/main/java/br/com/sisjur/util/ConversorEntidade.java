package br.com.sisjur.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("conversorEntidade")
public class ConversorEntidade implements Converter {

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            return component.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent component, Object value) {
        if (value != null && !"".equals(value)) {
            try {
                Object id = value.getClass().getMethod("getId").invoke(value);
                String idStr = String.valueOf(id);
                component.getAttributes().put(idStr, value);
                return idStr;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
