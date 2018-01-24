package org.dcm4chee.arc.knd.common.servlet.userDefinedServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserDefinedServlet2 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -632698193787409593L;
	
	@Override
	protected void doGet(HttpServletRequest rest,HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("自定义Servlet2重写doGet方法......");
		doPost(rest,resp);
	}
   
	@Override
	protected void doPost(HttpServletRequest rest,HttpServletResponse resp) throws IOException {
		System.out.println("自定义Servlet2重写doPost方法......");
		resp.setCharacterEncoding("utf-8");
		resp.setHeader("Content-type", "text/json;charset=utf-8");
		resp.setContentType("text/json");
		    PrintWriter pw = resp.getWriter();
			pw.write("自定义Servlet2返回的数据......");
			pw.close();
	}
}
