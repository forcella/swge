package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import controller.ControleTipoUnidade;
import model.TipoUnidade;

@FacesConverter(forClass = TipoUnidade.class,value = "tipoConverter")
public class TipoConverter implements Converter{
	
	private ControleTipoUnidade controlTpUnidade;
	
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {

		controlTpUnidade = new ControleTipoUnidade();
		TipoUnidade tipoUnidade = controlTpUnidade.buscarPorId(Long.valueOf(string));

		return tipoUnidade;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((TipoUnidade) object).getId());
		} else {
			return null;
		}
	}
	
}
