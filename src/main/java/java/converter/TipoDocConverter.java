package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import controller.ControleTipoDocumento;
import model.TipoDocumento;
@FacesConverter(forClass = TipoDocumento.class, value = "tipoDocConverter")
public class TipoDocConverter implements Converter{

	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {

		ControleTipoDocumento controleTipoDocumento = new ControleTipoDocumento();
		TipoDocumento tipoDocumento = controleTipoDocumento.buscarPorId(Long.valueOf(string));

		return tipoDocumento;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((TipoDocumento) object).getId());
		} else {
			return null;
		}
	}
	
}
