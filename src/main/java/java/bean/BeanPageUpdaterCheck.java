package bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import model.PageUpdater;

/**
 * @author lucas39151
 *
 */
@ManagedBean
@SessionScoped
public class BeanPageUpdaterCheck implements Serializable {

	private static final long serialVersionUID = 3902191501141655293L;

	@ManagedProperty(value = "#{beanPageUpdaterNotify}")
	private BeanPageUpdaterNotify beanPageUpdaterNotify;

	@ManagedProperty(value = "#{beanProduto}")
	private BeanProduto beanProduto;

	@ManagedProperty(value = "#{beanPdv}")
	private BeanPdv beanPdv;

	@ManagedProperty(value = "#{beanConta}")
	private BeanConta beanConta;

	private PageUpdater pageUpdater;
	private String localToUpdate;

	public BeanPageUpdaterCheck() {
		if (beanPageUpdaterNotify == null) {
			beanPageUpdaterNotify = new BeanPageUpdaterNotify();
		}
		pageUpdater = new PageUpdater();
		setLocalToUpdate("");

	}

	public void checkChanges() {
		System.out.println("Testou");
		
		if (beanPageUpdaterNotify.getPageUpdater().getPageToUpdate().get("pdv") != pageUpdater.getPageToUpdate()
				.get("pdv")) {
			beanPdv.updateLists();
			setLocalToUpdate("conteudo:formPdv:tableProdLancar");
			setLocalToUpdate("pdv");
			RequestContext.getCurrentInstance().update("conteudo");
			pageUpdater.getPageToUpdate().replace("pdv", beanPageUpdaterNotify.getPageUpdater().getPageToUpdate().get("pdv"));
			
		}

		else if (beanPageUpdaterNotify.getPageUpdater().getPageToUpdate().get("caixa") != pageUpdater.getPageToUpdate()
				.get("caixa")) {
			beanConta.updateTodasContas();
			setLocalToUpdate("conteudo:formCaixa");
			RequestContext.getCurrentInstance().update("conteudo");
			pageUpdater.getPageToUpdate().replace("caixa", beanPageUpdaterNotify.getPageUpdater().getPageToUpdate().get("caixa"));
			
		}

		else if (beanPageUpdaterNotify.getPageUpdater().getPageToUpdate().get("produto") != pageUpdater.getPageToUpdate()
				.get("produto")) {
			beanProduto.updateLists();
			setLocalToUpdate("conteudo");
			RequestContext.getCurrentInstance().update("conteudo:formEdit:tableDATA");
			pageUpdater.getPageToUpdate().replace("produto", beanPageUpdaterNotify.getPageUpdater().getPageToUpdate().get("produto"));
			
		}else {
			setLocalToUpdate("");
		}
		System.out.println("Local:"+localToUpdate);
		System.out.println(beanPageUpdaterNotify.getPageUpdater().getPageToUpdate().get("caixa") + "  " + pageUpdater.getPageToUpdate().get("caixa"));

	}

	public String getLocalToUpdate() {
		return localToUpdate;
	}

	public void setLocalToUpdate(String localToUpdate) {
		this.localToUpdate = localToUpdate;
	}

	public BeanPageUpdaterNotify getBeanPageUpdaterNotify() {
		return beanPageUpdaterNotify;
	}

	public void setBeanPageUpdaterNotify(BeanPageUpdaterNotify beanPageUpdaterNotify) {
		this.beanPageUpdaterNotify = beanPageUpdaterNotify;
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

	public BeanProduto getBeanProduto() {
		return beanProduto;
	}

	public void setBeanProduto(BeanProduto beanProduto) {
		this.beanProduto = beanProduto;
	}

	public BeanConta getBeanConta() {
		return beanConta;
	}

	public void setBeanConta(BeanConta beanConta) {
		this.beanConta = beanConta;
	}

	public BeanPdv getBeanPdv() {
		return beanPdv;
	}

	public void setBeanPdv(BeanPdv beanPdv) {
		this.beanPdv = beanPdv;
	}

	
	
}
