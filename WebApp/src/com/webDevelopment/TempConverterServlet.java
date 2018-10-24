package com.webDevelopment;



/* Overview of the web app's parts, which is now an HttpServlet rather than a GenericServlet:
   
   style.css            -- visual design for input.html 
   web.xml              -- configuration, especially the servlet mapping on a request
   input.html           -- user input with client-side data validation via HTML5
   TempConverterServlet -- data validation and app logic
   convert.jsp          -- template for responding to the client
 */

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TempConverterServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
	String temp = req.getParameter("tempIn");   // retrieve user input
	String result = validateAndConvert(temp);   // validate user input and convert
	redirectResultToClient(req, res, result);
    }

    //*** This is controversial in that it breaks 'Restful' principles by having a 
    //    GET request (a 'read') and a POST request (a 'create') perform exactly
    //    the same logic.
    //    However, it's convenient. :)
    //    One option would be not to override doGet; another would be to throw
    //    an exception to signal that GET requests aren't allowed in this web app.
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
	this.doPost(req, res); // delegate the request to the doPost method
    }

    private String validateAndConvert(String temp) {
	String result = "Bad input";
	try {
	    float t = Float.parseFloat(temp);
	    float f = c2f(t);
	    float c = f2c(t);
	    result = temp + " == " + 
		String.valueOf(c) + "F : " + String.valueOf(f) + "C";
	}
	catch (NumberFormatException e) { }
	return result;
    }

    private float f2c(float t) {
	return (5.0f / 9.0f) * (t - 32.0f);
    }

    private float c2f(float t) {
	return (9.0f / 5.0f * t) + 32.0f;
    }

    /*
      The servlet, in this implementation, does not 'forward' to the 'convert.jsp' script, but
      instead does a redirect: the client's agent (typically a browser) is contacted and told to
      'redirect' to the 'convert.jsp' script. 

      This results in an altogether new HTTP request; hence, the servlet can't simply forward the
      old request. A different approach is needed.

      In this implementation, the servlet uses the HttpSession, which is visible across all of the
      pages in this web app, to store the result. The JSP script then accesses the result from 
      the HttpSession--the JSP access code is exactly the same as before: ${result}

      The HttpSession is thread-safe, and automatically garbage-collected once the client session ends.
     */
    private void redirectResultToClient(HttpServletRequest req, HttpServletResponse res, String result) {
	try {
	    HttpSession session = req.getSession();
	    session.setAttribute("result", result);       // save result in the session map
	    res.sendRedirect("convert.jsp");           // redirect to 'convert.jsp'
	}
	catch(IOException e) { }
    }
}