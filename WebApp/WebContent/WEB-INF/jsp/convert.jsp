<%@ page errorPage = "error.jsp" %>
<!doctype html>
<html>
  <head>
    <title>Temp conversions</title>
    <link rel="stylesheet" type="text/css" href="style.css">
  </head>
  <body>
    <fieldset>
      <legend class = 'legend'>Temperature conversion results</legend>
      <p class = "results">
	   ${result}
      </p>
    </fieldset> 
    <p>
      <a href = 'input.html'>Convert another temperature</a>
    </p>
  </body>
</html>
