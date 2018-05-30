<html>
<head>
    <title>Calculator Servlet</title>
</head>
<body> 

	  <a>Hello, this is a simple calculator</a>
    <br/><br/>
     
    <form action="CalcServlet" method="post">
        Number 1: <input type="text" size="10" name="opd1"/>
        &nbsp;&nbsp;
        <p>
        Number 2: <input type="text" size="10" name="opd2"/>
        &nbsp;&nbsp;
        <p>
        <input type="submit" name = "op" value="Add" />
        <input type="submit" name = "op" value="Subtract" />
        <input type="submit" name = "op" value="Multiply" />
        <input type="submit" name = "op" value="Divide" />
    </form>
</body>
</html>