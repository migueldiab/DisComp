<h1>Usuario</h1>
<%@ page import="dao.Usuario" %>
<%
Usuario unUsuario = (Usuario) request.getAttribute( "unUsuario" );
%>
[<a href="./usuario.Listar.cmd">Lista de Usuarios</a>]
<hr/>

<form action="action" method="post">
  <table class="bodyTable">
  <tr>
    <input type="hidden" id="action" name="action" value="guardarUsuario"/>
    <input type="hidden" id="id" name="id" value="<%= unUsuario.getId() %>"/>
    <td>Nombre:</td>
    <td><input type="text" id="nombre" name="nombre" size="30" value="<%= unUsuario.getNombre() %>"/></td>
  </tr>
  <tr>
  </table>
  <input type="submit" value="Guardar">
  [<a href="action?action=borrarUsuario&id=<%= unUsuario.getId() %>">Borrar</a>]
</form>
