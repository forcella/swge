package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import controller.ControleCategoria;
import model.Categoria;

@FacesConverter(value="categoriaConverter")
public class CategoriaConverter implements Converter {
		
		ControleCategoria controlerCategoria = new ControleCategoria();
	
	    public Object getAsObject(FacesContext context, UIComponent component,String value) {                

	        if(value == null || value.isEmpty()){
	            return null;
	        }else{
	            Long id = Long.parseLong(value);
	            Categoria categoria = controlerCategoria.buscarPorId(id);
	            return categoria;
	        }

	    }

	
	    public String getAsString(FacesContext context, UIComponent component,
	            Object value) {
	        if (value instanceof Categoria) {
				Categoria categoria = (Categoria) value;
				 return String.valueOf(categoria.getId());
			}else{
	            return "";
	        }
	    }

	}