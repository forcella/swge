package bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import controller.ControleTipoUnidade;
import model.TipoUnidade;


@ManagedBean
public class BeanTipoUnidade {
	
	TipoUnidade tpUnidade;
	
	public BeanTipoUnidade(){
		setTpUnidade(new TipoUnidade());	
	}
	
	public void cadastrar(){
		
		ControleTipoUnidade controlTpUnidade = new ControleTipoUnidade();
		tpUnidade.setNome(tpUnidade.getNome().toUpperCase());
		tpUnidade = controlTpUnidade.cadastrar(tpUnidade);
		if (tpUnidade == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Nao foi possivel cadastrar a categoria"));
		} else {
			 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Categoria Cadastrada Com Sucesso"));
		}
		tpUnidade = new TipoUnidade();
		
	}
	
	public void limpar(){
		tpUnidade = new TipoUnidade();
	}
	public TipoUnidade getTpUnidade() {
		return tpUnidade;
	}

	public void setTpUnidade(TipoUnidade tpUnidade) {
		this.tpUnidade = tpUnidade;
	}
	
	
	
	
}
