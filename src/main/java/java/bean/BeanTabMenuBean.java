package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.component.menubar.Menubar;
import org.primefaces.component.tabmenu.TabMenu;
import org.primefaces.component.tabview.Tab;
import org.primefaces.component.tabview.TabView;

@ManagedBean
@SessionScoped
public class BeanTabMenuBean {

	private TabView tabView;

	public TabView getTabView() {
		return tabView;
	}

	public void setTabView(TabView tabView) {
		this.tabView = tabView;
	}

	public BeanTabMenuBean() {
		tabView = new TabView();
		Tab tab1 = new Tab();
		tab1.setTitle("Business Partner");
		Tab tab2 = new Tab();
		tab2.setTitle("Manage Favorites");
		Tab tab3 = new Tab();
		tab3.setTitle("subtab");
		TabView tabView2 = new TabView();
		tabView2.getChildren().add(tab3);
		tab1.getChildren().add(tab1);
		tab1.getChildren().add(tab2);
		tab2.getChildren().add(tabView2);
		tab2.getChildren().add(tabView2);
		tabView.getChildren().add(tab1);
		tabView.getChildren().add(tab2);
	}

}
