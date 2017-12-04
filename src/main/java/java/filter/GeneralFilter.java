package filter;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.BeanLogin;

@WebFilter("/*")
@ManagedBean
public class GeneralFilter implements Filter {
	private BeanLogin beanLogin;
	
	private Boolean logado;
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter (ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException{
		 HttpServletRequest request = (HttpServletRequest) req;
	     HttpServletResponse response = (HttpServletResponse) res;
	    
	    HttpSession session = ((HttpServletRequest) req).getSession(false);
	    beanLogin = (session != null) ? (BeanLogin) session.getAttribute("beanLogin"): null;
	     
	     
	     try {
			logado = beanLogin.getLogado();
		} catch (Exception e) {
		
			logado = false;
		}
	    
	    //System.out.println(beanLogin.getLogado());
		String uri = ((HttpServletRequest)request).getRequestURI();
	        if (uri.indexOf("/app.xhtml")>0 || uri.indexOf("/javax.faces.resource")>0
	        		|| uri.indexOf("/img")>0){
	            chain.doFilter(request, response);
	        }else if(uri.indexOf("/index.xhtml") > 0  &&  !logado) {
	        	chain.doFilter(request, response);
	        }else{
	        	response.sendRedirect(request.getContextPath()+"/app.xhtml");
	        }
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


	public BeanLogin getBeanLogin() {
		return beanLogin;
	}


	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
	}

}
