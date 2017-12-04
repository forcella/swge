package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import controller.ControleCartao;
import model.Cartao;

@FacesConverter(value = "cartaoConverter")
public class CartaoConverter  implements Converter{


	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		ControleCartao controleCartao = new ControleCartao();
		Cartao cartao = controleCartao.buscarPorId(Long.valueOf(string));
		return cartao;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((Cartao) object).getId());
		} else {
			return null;
		}
	}
}

