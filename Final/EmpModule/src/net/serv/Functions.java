package net.serv;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/functions")
public class Functions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html";
	
    public Functions() throws ClassNotFoundException, SQLException {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
	    PrintWriter out = response.getWriter();
	    out.println("<html>");
	    out.println("<head>");
	    out.println("</head>");
	    out.println("<body>");


	    out.println("<form method=post action=/servlet/serv.Functions>");
	    out.println("Hello, this is an employee database");
	    out.println("<p>Employee ID: <input type=text name=eID cols=10>");
	    out.println("</form>");
	    
	    out.println("<p> <input type=submit name=op value=Find>");
	    out.println(" <input type=submit name=op value=Add >");
	    out.println(" <input type=submit name=op value=Remove >");
	    out.println(" <input type=submit name=op value=Update >");
	    out.println(" <input type=submit name=op value=FindAll >");
	    out.println(" <input type=submit name=op value=Logout >");
	    out.println("</form>");

	    out.println("</body>");
	    out.println("</html> <p>");
	    out.flush();
	    
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		String in = request.getParameter("eID");
		String operation = request.getParameter("op");
		
		if(operation.equals("Add"))
		{
			///
		}
	}

}
