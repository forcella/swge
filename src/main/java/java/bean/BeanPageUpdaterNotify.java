package bean;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import model.PageUpdater;

@ManagedBean
@ApplicationScoped
public class BeanPageUpdaterNotify  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2684499441342348602L;
	private PageUpdater pageUpdater;
	
	public BeanPageUpdaterNotify() {
		pageUpdater = new PageUpdater();
	}
	
	public void notfyUpdatePdv() {	
		pageUpdater.getPageToUpdate().replace("pdv", new Date());
	}
	public void notfyUpdateCaixa() {	
		pageUpdater.getPageToUpdate().replace("caixa", new Date());
		System.out.println("Passou por notify caixa" + " "+ pageUpdater.getPageToUpdate().get("caixa"));
	}
	public void notfyUpdateProduto() {	
		pageUpdater.getPageToUpdate().replace("produto", new Date());
		System.out.println("Passou por notify produto" + " "+ pageUpdater.getPageToUpdate().get("produto"));
	}
	
	public PageUpdater getPageUpdater() {
		return pageUpdater;
	}
	public void setPageUpdater(PageUpdater pageUpdater) {
		this.pageUpdater = pageUpdater;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
