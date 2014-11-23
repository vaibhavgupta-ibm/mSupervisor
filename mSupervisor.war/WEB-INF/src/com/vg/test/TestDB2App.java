package com.vg.test;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * Servlet implementation class TestDB2App
 */
public class TestDB2App extends HttpServlet {
	private static final long serialVersionUID = 1L;

	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestDB2App() {
        super();
        // TODO Auto-generated constructor stub
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
		

	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try
		{
			out.println("<html>");
			
			out.println("Dropping  table");			
			dropTable("",out);
			
			out.println("Now creating table");			
			createTable("",out);	
			
			out.println("Now inserting records in records");
			insertRecords("", out);
			
			//
			//updateRecords("", out);
			
			out.println("Now fetching records table");
			viewRecords("", out);
			
			out.println("</html>");
			
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	private void insertRecords(String sql , PrintWriter out) throws Exception
	{
		
				out.println("<br/>");
				out.println("trying to connect db..");
				out.println("<br/>");
				try
				{
			        Class. forName ( "COM.ibm.db2os390.sqlj.jdbc.DB2SQLJDriver"  );
			        Connection  connection =
	        		DriverManager.getConnection("jdbc:db2://23.246.228.243:50000/I_694049","tfdrubzh","3fkbkahqatps");
			        
			       sql =
	    			" INSERT INTO msupervisor_data "+
					" (record_id,name_data_provider,identity_code,site_name,status_txt,photo_name,photo_long_lat" +
					" photo_timestamp,comments_data_provider,supervisor_id,supervisor_name," +
					" supervisor_approved,current_status,supervisor_comments,record_created_on,record_last_updated_on)"+
					" VALUES " +
					" (DEFAULT,'ABC','aacc12','PQR','COMPLETED','ewigdieg.jpeg','1112223.22,22334.89','20141122163111'," +
					" 'test comment for this data','ss123','SUP1','Y','CLOSED_COMPLETED','approved this record',CURRENT TIMESTAMP,CURRENT TIMESTAMP)";
			        
			        
			        out.println("Inserting records in table in given database...");
			        out.println("<br/>");
			        Statement stmt = connection.createStatement();
			        int r = stmt.executeUpdate(sql);
			        
			        out.println(" done , records update="+r);
			        out.println("<br/>");

					connection.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
		
	}
	
	
	private void dropTable(String sql , PrintWriter out) throws Exception
	{
		
		sql = "DROP TABLE msupervisor_data";
		out.println("<br/>");
		out.println("trying to connect db..");
		out.println("<br/>");
		try
		{
	        Class. forName ( "COM.ibm.db2os390.sqlj.jdbc.DB2SQLJDriver"  );
	        Connection  connection =
	                //DriverManager.getConnection("jdbc:db2://serverName:portNumber/DatabaseName","userName","password");
	        		DriverManager.getConnection("jdbc:db2://23.246.228.243:50000/I_694049","tfdrubzh","3fkbkahqatps");
	      
	        
	        out.println("dropping table in given database...");
	        out.println("<br/>");
	        Statement stmt = connection.createStatement();
	        stmt.executeUpdate(sql);
	        out.println("done...");

			connection.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		
	}
	
	private void createTable(String sql , PrintWriter out) throws Exception
	{
		
			sql = 
			"CREATE TABLE msupervisor_data ("+ 
					   "record_id  				INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1110 INCREMENT BY 1 MINVALUE 1 NO MAXVALUE NO CYCLE NO CACHE ORDER),"+ 
					   "name_data_provider 		VARCHAR(50),"+
					   "identity_code			VARCHAR(10),"+
					   "site_name				VARCHAR(50),"+
					   "status_txt				VARCHAR(20),"+	
					   "photo_name			    VARCHAR(100),"+	
					   "photo_file   			BLOB(5M),"+
					   "photo_long_lat			VARCHAR(100),"+	
					   "photo_timestamp			CHAR(14),"+
					   "comments_data_provider	VARCHAR(500),"+	
					   "supervisor_id			VARCHAR(10),"+	
					   "supervisor_name			VARCHAR(50),"+
					   "supervisor_approved		CHAR(1),"+	
					   "current_status			VARCHAR(20),"+	
					   "supervisor_comments		VARCHAR(500),"+	
					   "record_created_on		TIMESTAMP,"+
					   "record_last_updated_on	TIMESTAMP,"+
					   "PRIMARY KEY (record_id))";

				
				out.println("<br/>");
				out.println("trying to connect db..");
				out.println("<br/>");
				try
				{
			        Class. forName ( "COM.ibm.db2os390.sqlj.jdbc.DB2SQLJDriver"  );
			        Connection  connection =
			        		DriverManager.getConnection("jdbc:db2://23.246.228.243:50000/I_694049","tfdrubzh","3fkbkahqatps");
			      
			        
			        out.println("Creating table in given database...");
			        out.println("<br/>");
			        Statement stmt = connection.createStatement();
			        stmt.executeUpdate(sql);
			        out.println("done..");
			        out.println("<br/>");

					connection.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
		
	}
	
	private void updateRecords(String sql , PrintWriter out) throws Exception
	{
		
	}
	
	private void viewRecords(String sql , PrintWriter out) throws Exception
	{
		//sql = "SELECT current timestamp FROM sysibm.sysdummy1";
		
		sql = "SELECT record_id,name_data_provider,identity_code,site_name,status_txt,photo_name,photo_long_lat" +
					" photo_timestamp,comments_data_provider,supervisor_id,supervisor_name," +
					" supervisor_approved,current_status,supervisor_comments,record_created_on,record_last_updated_on";
		
		out.println("<br/>");
		out.println("trying to connect db..");
		out.println("<br/>");
		try
		{
	        Class. forName ( "COM.ibm.db2os390.sqlj.jdbc.DB2SQLJDriver"  );
	        Connection  connection =
	        		DriverManager.getConnection("jdbc:db2://23.246.228.243:50000/I_694049","tfdrubzh","3fkbkahqatps");
	      
	        out.println("<br/>");
	        out.println( "db2 connection obtained.. " );
	        out.println("<br/>");
	        out.println( "firing query.. " );
	        out.println("<br/>");
	        out.println( ""+sql );
	        
	        Statement stmt = connection.createStatement();
	        
	        ResultSet rs =  stmt.executeQuery(sql);
	        
	        while(rs.next())
	        {
	        	String op = rs.getString(1)  +"\n "+rs.getString(2)  + "\n "+rs.getString(3)+ " "+ 
	        				rs.getString(4)  +"\n "+rs.getString(5)  + "\n "+rs.getString(6)+ " "+
	        				rs.getString(7)  +"\n "+rs.getString(8)  + "\n "+rs.getString(9)+ " "+
	        				rs.getString(10) +"\n "+rs.getString(11) + "\n "+rs.getString(12)+ " "+
	        				rs.getString(13) +"\n "+rs.getString(14) + "\n "+rs.getString(15)+ " "+rs.getString(16) ;
	        	
	        	out.println("<br/>");
	    		out.println("Output: "+op);
	        }
			out.println("<br/>");
			out.println("<a href='javascript:location.reload();'>Refresh</a>");

			connection.close();
			out.println("done..");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			out.println(e.getMessage());
		}

	}
	
}
