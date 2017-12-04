package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import controller.ControleProduto;
import model.Produto;

@FacesConverter(value = "produtoConverter")
public class ProdutoConverter implements Converter {

	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		ControleProduto controleProduto = new ControleProduto();
		Produto produto = controleProduto.buscarPorId(Long.valueOf(string));
		return produto;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((Produto) object).getId());
		} else {
			return null;
		}
	}
}