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

import com.fasterxml.jackson.databind.ObjectMapper;

public class HTTPBasicAuthorizeAttribute implements Filter {
   private static String Name = "test";
   private static String Password = "test";
   
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		System.out.println("自定义过滤器...............");
		// TODO Auto-generated method stub
		  ResultStatusCode resultStatusCode = checkHTTPBasicAuthorize(arg0);  
	        if (true)  //resultStatusCode != ResultStatusCode.OK
	        {  
	            HttpServletResponse httpResponse = (HttpServletResponse) arg1;  
	            httpResponse.setCharacterEncoding("UTF-8");    
	            httpResponse.setContentType("application/json; charset=utf-8");   
	            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  
	  
	            ObjectMapper mapper = new ObjectMapper();  
	              
//	            ResultMsg resultMsg = new ResultMsg(ResultStatusCode.PERMISSION_DENIED.getErrcode(), ResultStatusCode.PERMISSION_DENIED.getErrmsg(), null);  
	            httpResponse.getWriter().write(mapper.writeValueAsString(null));  
	            return;  
	        }  
	        else  
	        {  
	        	arg2.doFilter(arg0, arg1);  
	        }  
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
	
	private ResultStatusCode checkHTTPBasicAuthorize(ServletRequest request)  
    {  
        try  
        {  
            HttpServletRequest httpRequest = (HttpServletRequest)request;  
            String auth = httpRequest.getHeader("Authorization");  
            if ((auth != null) && (auth.length() > 6))  
            {  
                String HeadStr = auth.substring(0, 5).toLowerCase();  
                if (HeadStr.compareTo("basic") == 0)  
                {  
                    auth = auth.substring(6, auth.length());    
                    String decodedAuth = getFromBASE64(auth);  
                    if (decodedAuth != null)  
                    {  
                        String[] UserArray = decodedAuth.split(":");  
                          
                        if (UserArray != null && UserArray.length == 2)  
                        {  
                            if (UserArray[0].compareTo(Name) == 0  
                                    && UserArray[1].compareTo(Password) == 0)  
                            {  
                                return null;//ResultStatusCode.OK;  
                            }  
                        }  
                    }  
                }  
            }  
            return null;//ResultStatusCode.PERMISSION_DENIED;  
        }  
        catch(Exception ex)  
        {  
            return null;//ResultStatusCode.PERMISSION_DENIED;  
        }  
          
    }  
      
    private String getFromBASE64(String s) {    
        if (s == null)    
            return null;    
//        BASE64Decoder decoder = new BASE64Decoder();    
        try {    
            byte[] b = null;//decoder.decodeBuffer(s);    
            return new String(b);    
        } catch (Exception e) {    
            return null;    
        }    
    }  

}
