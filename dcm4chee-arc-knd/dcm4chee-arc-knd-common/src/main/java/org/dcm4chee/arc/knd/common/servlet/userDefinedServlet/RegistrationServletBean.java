package org.dcm4chee.arc.knd.common.servlet.userDefinedServlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 该类作用:@Component注解将该注册管理类统扫描到spring容器，使用ServletRegistrationBean包装和管理自定义的Servlet，通过@Bean注解使
 * 返回的ServletRegistrationBean能被spring容器管理
 * 使用ServletRegistrationBean统一进行注册，可以注册多个，注意@Bean注解
 * @author Administrator
 */
@Component
public class RegistrationServletBean {
   
	@Bean
	public ServletRegistrationBean servletRegistrationBean1() {
		return new ServletRegistrationBean(new UserDefinedServlet(),"/servlet1/*");// ServletName默认值为首字母小写，即userDefinedServlet  
	}
	
	@Bean
	public ServletRegistrationBean servletRegistrationBean2() {
		return new ServletRegistrationBean(new UserDefinedServlet2(),"/servlet2/*");// ServletName默认值为首字母小写，即userDefinedServlet2  
	}
	
	  /** 
	    * 修改DiapatcherServlet默认拦截"/":
	    * @param dispatcherServlet 
	    * @return 
	    * @author SHANHY 
	    * @create  2016年1月6日 
	    */  
	
//	   @Bean  
//	   public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {  
//	       ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);  
//	       registration.getUrlMappings().clear();  
//	       registration.addUrlMappings("*.do");  
//	       registration.addUrlMappings("*.json");  
//	       return registration;  
//	   }  
	
}
