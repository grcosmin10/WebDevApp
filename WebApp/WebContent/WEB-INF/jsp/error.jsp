<%@ page isErrorPage = "true" 
	 import = "java.io.*" 
	 contentType = "text/plain"%>

Message: <%=exception.getMessage()%>

StackTrace:
<%
	StringWriter stringWriter = new StringWriter();
	PrintWriter printWriter = new PrintWriter(stringWriter);
	exception.printStackTrace(printWriter);
        out = null;
	out.println(stringWriter);
	printWriter.close();
	stringWriter.close();
%>