package com.vg.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet implementation class Hello
 */
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hello() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String _user_name = request.getParameter("name");
		String _pwd = request.getParameter("pwd");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		
		if(_user_name !=null && _pwd !=null &&_user_name.equals("vaibhav") && _pwd.equals("gupta")){

			out.println("<html>");
			out.println("<br/>");out.println("<br/>");
			out.println("Welcome :"+_user_name + " you are authenticated successfully!");
			out.println("<br/>");
			out.println("<br/>");
			out.println("<a href='Hello.jsp'>Back</a>");
			out.println("<br/>");
			out.println("<a href='javascript:location.reload();'>Refresh</a>");
			out.println("</html>");

		}
		else
		{
			out.println("<html>");
			out.println("<br/>");out.println("<br/>");
			out.println("Sorry :"+_user_name + " authentication failed!");
			out.println("<br/>");
			out.println("<br/>");
			out.println("<a href='Hello.jsp'>Back</a>");
			out.println("<br/>");
			out.println("<a href='javascript:location.reload();'>Refresh</a>");
			out.println("</html>");

			
		}
				
	}

}
