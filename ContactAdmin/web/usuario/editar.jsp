<h1>Usuario</h1>
<%@ page import="dao.Usuario" %>
<%
Usuario unUsuario = (Usuario) request.getAttribute( "usuario" );
%>
[<a href="./usuario.Listar.cmd">Lista de Usuarios</a>]
<hr/>

<form action="usuario.Guardar.cmd" method="post">
  <input type="hidden" id="id" name="id" value="<%= unUsuario.getId() %>"/>
  <table class="bodyTable">
    <tr>
      <td>Nombre:</td>
      <td><input type="text" id="nombre" name="nombre" size="30" value="<%= unUsuario.getNombre() %>"/></td>
    </tr>
    <tr>
      <td>Apellido:</td>
      <td><input type="text" id="apellido" name="apellido" size="30" value="<%= unUsuario.getApellido() %>"/></td>
    </tr>
    <tr>
      <td>Fecha de Nacimiento:</td>
      <td>
        <input type="text" id="date" name="date" size="2" value="<%= unUsuario.getFechaDeNacimiento().getDate() %>"/>/
        <input type="text" id="month" name="month" size="2" value="<%= unUsuario.getFechaDeNacimiento().getMonth() %>"/>/
        <input type="text" id="year" name="year" size="2" value="<%= unUsuario.getFechaDeNacimiento().getYear() %>"/>
        (dd/mm/aa)
      </td>
    </tr>
  </table>
  <input type="submit" value="Guardar">
  [<a href="usuario.Borrar.cmd?id=<%= unUsuario.getId() %>">Borrar</a>]
</form>
