/*
	Programmer: Josh Long
	Assignment: 9 Servlet Calculator
	Date: 4/22/16
 */

package net.serv;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/CalcServlet")
public class CalcServlet extends HttpServlet
{
  private static final String CONTENT_TYPE = "text/html";
  /**Initialize global variables*/
  public void init() throws ServletException
  {

  }
  /**Process the HTTP Get request*/
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    response.setContentType(CONTENT_TYPE);
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<head>");
    out.println("</head>");
    out.println("<body>");


    out.println("<form method=post action=/servlet/serv.CalcServlet>");
    out.println("Hello, this is a simple calculator");
    out.println("<p>Number 1: <input type=text name=opd1 cols=10>");
    out.println("<p>Number 2: <input type=text name=opd2 cols=10>");

    out.println("<p> <input type=submit name=op value=Add >");
    out.println(" <input type=submit name=op value=Subtract >");
    out.println(" <input type=submit name=op value=Multiply >");
    out.println(" <input type=submit name=op value=Divide >");
    out.println("</form>");

    out.println("</body>");
    out.println("</html>");
    out.flush();
  }
  /**Process the HTTP Post request*/
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
	  ///response.setContentType(CONTENT_TYPE);
	  PrintWriter out = response.getWriter();
	  String in = request.getParameter("opd1");
	  double num1 = Double.parseDouble(in);
	  in = request.getParameter("opd2");
	  double num2 = Double.parseDouble(in);
	  String operation = request.getParameter("op");
	  double res=0;
	  if (operation.equals("Add") )
	  {
	      res = num1 + num2;
	  }
	  if (operation.equals("Subtract") )
	  {
		  res = num1 - num2;
	  }
	  if  (operation.equals("Multiply") )
	  {
	      res = num1  *num2;
	  }
	  if (operation.equals("Divide") )
	  {
	      res = num1 / num2;
	  }


	  out.println("<html>");
	  out.println("<head><title>CalcServlet</title></head>");
	  out.println("<body>");
	  out.println("<p>The servlet has received a POST. This is the reply.</p>");
	  out.println("I received number 1: " + num1);
	  out.println("I received number 2: " + num2);
	  out.println("The result is: " + res);
	  out.println("<p> <p> <a href=/CalculatorServlet/net.serv.CalcServlet> Go To Start</a>");
	  out.println("</body></html>");
	  out.flush();
  }
  /**Clean up resources*/
  public void destroy()
  {
	  System.out.println("Servlet is being destroyed");
  }
}