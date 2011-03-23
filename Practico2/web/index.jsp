<%-- 
    Document   : index
    Created on : Mar 22, 2011, 9:32:12 PM
    Author     : alumno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="main.css" media="screen" /></link>
    </head>
    <body>
        <h1>Bienvenido al saludador!</h1>
        <center>
          <form action="./saludar" method="post">
            <h4>Ingresá tu nombre</h4>
            <input id="name" name="name" />
            <button type="submit">Saludar</button>
            <br/>
            <a href="http://migueldiab.deviantart.com">Visitanos en DeviantArt!</a>
          </form>
        </center>
        
    </body>
</html>
