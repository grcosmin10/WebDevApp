package com.webDevelopment;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/* Overview of the web app's parts:

style.css            -- visual design for input.html 
web.xml              -- configuration, especially the servlet mapping on a request
input.html           -- user input with client-side data validation via HTML5
TempConverterServlet -- data validation and app logic
convert.jsp          -- template for responding to the client
*/

public class TempConverterServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		String temp = req.getParameter("tempIn"); // retrieve user input
		String result = validateAndConvert(temp); // validate user input and convert
		dispatchResultToClient(req, res, result);
	}

	private String validateAndConvert(String temp) {
		String result = "Bad Input";
		try {
			float t = Float.parseFloat(temp);
			float f = c2f(t);
			float c = f2c(t);
			result = temp + " == " + String.valueOf(c) + "F : " + String.valueOf(f) + "C";

		} catch (NumberFormatException nfe) {

		}
		return result;
	}

	private float f2c(float t) {
		return (5.0f / 9.0f) * (t - 32.0f);
	}

	private float c2f(float t) {
		return (9.0f / 5.0f * t) + 32.0f;
	}

	private void dispatchResultToClient(ServletRequest req, ServletResponse res, String result) {
		try {
		    RequestDispatcher rd = req.getRequestDispatcher("convert.jsp");    // let the JSP handle the UI
		    req.setAttribute("result", result);                                // include this in the dispatch
		    rd.forward(req, res);                                              // pass along the orig request/response
		}
		catch(ServletException e) { }
		catch(IOException e) { }
	}
}
