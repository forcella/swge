package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.TipoMovimentacao;

@FacesConverter(value = "tipoMovConverter")    
public class TipoMovimentacaoConverter implements Converter {
 
  public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
      if (value != null && !value.isEmpty()) {
          return (TipoMovimentacao) uiComponent.getAttributes().get(value);
      }
      return null;
  }

  
  public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
      if (value instanceof TipoMovimentacao) {
          TipoMovimentacao entity= (TipoMovimentacao) value;
          if (entity != null && entity instanceof TipoMovimentacao && entity.getId() != null) {
              uiComponent.getAttributes().put( entity.getId().toString(), entity);
              return entity.getId().toString();
          }
      }
      return "";
  }
}
