<%@page import="java.util.List"%>
<h1>Listado de Usuarios</h1>
[<a href="./">Menu Principal</a>]
<hr/>
<%@ page import="dao.Usuario" %>
<%
List<Usuario> dto = (List<Usuario>) request.getAttribute( "usuarios" );
%>
<table border="1">
<tr>
	<td>Nombre</td>
	<td>Apellido</td>
	<td>Fecha de Nac.</td>
</tr>
<% for (Usuario value: dto) { %>
<tr>
  <td><a href="usuario.Editar.cmd?id=<%= value.getId() %>"><%= value.getNombre() %></a></td>
	<td><%= value.getApellido() %></td>
	<td><%= value.getFechaDeNacimiento() %></td>
</tr>
<% } %>
</table>
[<a href="usuario.Nuevo.cmd">Nuevo</a>]