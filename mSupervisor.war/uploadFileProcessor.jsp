<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.io.output.*" %>
<%@ page import = "javax.servlet.RequestDispatcher" %>
<%@ page import="java.sql.*" %>


<%
System.out.println("1 - Start");
  String name="",id="",site="",status="",comment="",loc="UNAVAILABLE",datetime="UNAVAILABLE",fileN="",fileL="" ;	
 

  String fieldsnvals="";
 
   File file ;
   int maxFileSize = 5000 * 1024;
   int maxMemSize = 5000 * 1024;
   ServletContext context = pageContext.getServletContext();
   String filePath = context.getInitParameter("file-upload");
   filePath = "";
   
System.out.println("2");

   // Verify the content type
   String contentType = request.getContentType();
   if ((contentType.indexOf("multipart/form-data") >= 0)) {
System.out.println("3");
      DiskFileItemFactory factory = new DiskFileItemFactory();
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
      // Location to save data that is larger than maxMemSize.
      factory.setRepository(new File("c:\\temp"));
System.out.println("4");
      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );
	  System.out.println("5");
      try{ 
         // Parse the request to get file items.
         List fileItems = upload.parseRequest(request);

         // Process the uploaded file items
         Iterator i = fileItems.iterator();

         while ( i.hasNext () ) 
         {
            FileItem fi = (FileItem)i.next();
            if ( !fi.isFormField () )	
            {
				// Get the uploaded file parameters
				String fieldName = fi.getFieldName();
				String fileName = fi.getName();
				boolean isInMemory = fi.isInMemory();
				long sizeInBytes = fi.getSize();
				// Write the file
				if( fileName.lastIndexOf("\\") >= 0 ){
				file = new File( filePath + 
				fileName.substring( fileName.lastIndexOf("\\"))) ;
				}else{
				file = new File( filePath + 
				fileName.substring(fileName.lastIndexOf("\\")+1)) ;
				}
				fi.write( file ) ;
				//System.out.println("Uploaded Filename: " + filePath + fileName);
				fieldsnvals  += " Uploaded Filename: " + filePath + fileName;
				fileN=fileName ; fileL=filePath;
            }
			else
			{
				// Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                String fieldname = fi.getFieldName();
                String fieldvalue = fi.getString();
				//System.out.println("fieldname="+fieldname);
				//System.out.println("fieldvalue="+fieldvalue);
				
				fieldsnvals += " fieldname="+fieldname	+ 	" fieldvalue="+fieldvalue ;
				
				if(fieldname.equals("name")) name = fieldvalue ;
				else if(fieldname.equals("id")) id = fieldvalue ;
				else if(fieldname.equals("site")) site = fieldvalue ;
				else if(fieldname.equals("status")) status = fieldvalue ;
				else if(fieldname.equals("comment")) comment = fieldvalue ;
				else if(fieldname.equals("loc")  && fieldvalue !=null && (!fieldvalue.equals("")) ) loc = fieldvalue ;
				else if(fieldname.equals("datetime")  && fieldvalue !=null && (!fieldvalue.equals("")) ) datetime = fieldvalue ;
				
			}
         }
		 System.out.println(fieldsnvals);
		 System.out.println("6 - Done");
		 
		 
		 
		 
				 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 %>
		 
		 <jsp:forward page="confirm.html" />
		 
		 <%
		 
      }catch(Exception ex) {
         System.out.println(ex);
      }
   }else{
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet upload</title>");  
      out.println("</head>");
      out.println("<body>");
      out.println("<p>No file uploaded</p>"); 
      out.println("</body>");
      out.println("</html>");
   }
%>