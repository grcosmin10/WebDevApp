<!doctype html>
<html>
  <head>
    <title>Exercise 1</title>
  </head>
<body>
  <jsp:useBean id = "now" class = "java.util.Date" /> 
  <jsp:useBean id = "hi" class = "com.webDevelopment.Hi" />
  <h3>
    <%= hi.getName() + " at " + now %>
  </h3>
</body>
</html>