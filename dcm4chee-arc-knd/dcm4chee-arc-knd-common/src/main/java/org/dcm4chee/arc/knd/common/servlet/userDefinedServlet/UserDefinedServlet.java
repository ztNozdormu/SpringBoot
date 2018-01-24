package org.dcm4chee.arc.knd.common.servlet.userDefinedServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserDefinedServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8469518529966404116L;
	
	  @Override
	  protected void doGet(HttpServletRequest rest,HttpServletResponse resp) throws IOException {
		  System.out.println("代码方式自定义Servlet,重写doGet方法......");
		  doPost(rest, resp);
	  }
	  
	  @Override
	  protected void doPost(HttpServletRequest rest,HttpServletResponse resp) throws IOException { 
		System.out.println("代码方式自定义Servlet,重写doPost方法......");  
		resp.setCharacterEncoding("utf-8");
		resp.setHeader("Content-type", "text/json;charset=utf-8");
		resp.setContentType("text/json");  
           PrintWriter pw = resp.getWriter();
           pw.print("自定义Servlet返回数据......");
           pw.close();
	  }
}
