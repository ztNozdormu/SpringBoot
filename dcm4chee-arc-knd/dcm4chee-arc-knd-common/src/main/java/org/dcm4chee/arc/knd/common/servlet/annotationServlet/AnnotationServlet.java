package org.dcm4chee.arc.knd.common.servlet.annotationServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/annotationServlet/annotation",description="注解方式定义Servlet",name="annotationServlet")
public class AnnotationServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4984930720110470206L;
	
	protected void doGet(HttpServletRequest rest,HttpServletResponse resp) throws IOException{
		System.out.println("注解方式实现自定义Servlet..........doGet");
		doPost(rest,resp);
	} 
 
	protected void doPost(HttpServletRequest rest,HttpServletResponse resp) throws IOException {
		System.out.println("注解方式实现自定义Servlet..........doPost");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/json");
		resp.setHeader("Content-type", "text/json;charset=utf-8");
		
	    PrintWriter pw = resp.getWriter();
	    pw.print("注解方式实现自定义的Servlet......doPost()");
	    pw.close();
	}
	
}
