package model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class PageUpdater  implements Serializable{
	

	private static final long serialVersionUID = 3952501269726026076L;
	private HashMap<String,Date> pageToUpdate;
	
	public PageUpdater() {
		pageToUpdate = new HashMap<String, Date>();
		pageToUpdate.put("pdv", null);
		pageToUpdate.put("caixa", null);
		pageToUpdate.put("produto", null);
		pageToUpdate.put("cliente", null);
				
	}

	public HashMap<String, Date> getPageToUpdate() {
		return pageToUpdate;
	}

	public void setPageToUpdate(HashMap<String, Date> pageToUpdate) {
		this.pageToUpdate = pageToUpdate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
