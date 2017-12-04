package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import controller.ControleCliente;
import model.Cliente;

@FacesConverter(value = "clienteConverter")
public class ClienteConverter implements Converter{

	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		ControleCliente controleCliente= new ControleCliente();
		Cliente cliente = controleCliente.buscarPorId(Long.valueOf(string));
		return cliente;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((Cliente) object).getId());
		} else {
			return null;
		}
	}
}

