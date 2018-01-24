package org.dcm4chee.arc.knd.common.filter.userDefinedFilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter implements Filter {  
		  
        private String[] excludedUris;  
  
        @Override  
        public void destroy() {  
            // TODO Auto-generated method stub  
        }  
  
        @Override  
        public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)  
                throws IOException, ServletException {  
        	System.out.println("自定义过滤器2..................");
            // TODO Auto-generated method stub  
            HttpServletRequest request = (HttpServletRequest) srequest;  
            HttpServletResponse response = (HttpServletResponse) sresponse;  
            System.out.println("this is MyFilter,url :"+request.getRequestURI());  
            String uri = request.getServletPath();  
            if(isExcludedUri(uri)){  
                filterChain.doFilter(srequest, sresponse);  
            }else if(request.getSession().getAttribute("user")!=null){  
                filterChain.doFilter(srequest, sresponse);  
            }else{  
                response.sendRedirect(request.getContextPath() + "/login/toLogin");  
            }  
        }  
  
        @Override  
        public void init(FilterConfig filterConfig) throws ServletException {  
            // TODO Auto-generated method stub  
            excludedUris = filterConfig.getInitParameter("excludedUri").split(",");  
        }  
  
        private boolean isExcludedUri(String uri) {  
            if (excludedUris == null || excludedUris.length <= 0) {  
                return false;  
            }  
            for (String ex : excludedUris) {  
                uri = uri.trim();  
                ex = ex.trim();  
                if (uri.toLowerCase().matches(ex.toLowerCase().replace("*",".*")))  
                    return true;  
            }  
            return false;  
        }


    }  

