package bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@ManagedBean
public class BeanMenuView {

	private MenuModel model;
	
    @PostConstruct
    public void init() {
        model = new DefaultMenuModel();
         
        //First submenu
        DefaultSubMenu caixaMenu = new DefaultSubMenu("Caixa");
         
        DefaultMenuItem item = new DefaultMenuItem("External");
        item.setUrl("http://www.primefaces.org");
        item.setIcon("ui-icon-home");
        caixaMenu.addElement(item);
         
        model.addElement(caixaMenu);
         
        //Second submenu
        DefaultSubMenu clienteMenu = new DefaultSubMenu("Cliente");
 
        item = new DefaultMenuItem("Cadastro");
        item.setIcon("ui-icon-disk");
        item.setCommand("#{beanPageLoader.loadCadastroCliente}");
        item.setAjax(true);
        item.setUpdate("conteudo");
        clienteMenu.addElement(item);
         
        item = new DefaultMenuItem("Controle");
        item.setIcon("ui-icon-close");
        item.setCommand("#{menuView.delete}");
        item.setAjax(true);
        item.setUpdate("conteudo");

        clienteMenu.addElement(item);
         
        item = new DefaultMenuItem("Editar");
        item.setIcon("ui-icon-search");
        item.setAjax(true);
        item.setUpdate("conteudo");
        
        item.setCommand("#{menuView.redirect}");
        clienteMenu.addElement(item);
 
        model.addElement(clienteMenu);
        
        DefaultSubMenu pdv = new DefaultSubMenu("Ponto de Venda");
        
        item = new DefaultMenuItem("Venda");
        item.setIcon("ui-icon-disk");
        item.setCommand("#{beanPageLoader.loadPdv}");
        item.setAjax(true);
        item.setUpdate("conteudo");
        pdv.addElement(item);
        
        model.addElement(pdv);
        
        DefaultSubMenu gerenteMenu = new DefaultSubMenu("Gerente");
        
        DefaultSubMenu produtoMenu = new DefaultSubMenu("Produto");
        
        item = new DefaultMenuItem("Cadastro");
        item.setIcon("ui-icon-disk");
        item.setCommand("#{beanPageLoader.loadCadastroProduto}");
        item.setAjax(true);
        item.setUpdate("conteudo");
        produtoMenu.addElement(item);
        gerenteMenu.addElement(produtoMenu);
        
        
        DefaultSubMenu funcionarioMenu = new DefaultSubMenu("Funcionario");
        
        item = new DefaultMenuItem("Cadastro");
        item.setIcon("ui-icon-disk");
        item.setCommand("#{beanPageLoader.loadCadastroFuncionario}");
        item.setAjax(true);
        item.setUpdate("conteudo");
        funcionarioMenu.addElement(item);
        gerenteMenu.addElement(funcionarioMenu);
        
        item = new DefaultMenuItem("Cadastro Categoria");
        item.setIcon("ui-icon-disk");
        item.setCommand("#{beanPageLoader.loadCadastroCategoria}");
        item.setAjax(true);
        item.setUpdate("conteudo");
        gerenteMenu.addElement(item);
        
        item = new DefaultMenuItem("Relatorios");
        item.setIcon("ui-icon-disk");
        item.setCommand("#{menuView.save}");
        item.setAjax(true);
        item.setUpdate("conteudo");
        gerenteMenu.addElement(item);
        
        model.addElement(gerenteMenu);
    }
 
    public MenuModel getModel() {
        return model;
    }   

	
}
