<h1>Usuario</h1>
[<a href="./usuario.Listar.cmd">Lista de Usuarios</a>]
<hr/>

<form action="usuario.Guardar.cmd" method="post">
  <table class="bodyTable">
    <tr>
      <td>Nombre:</td>
      <td><input type="text" id="nombre" name="nombre" size="30"/></td>
    </tr>
    <tr>
      <td>Apellido:</td>
      <td><input type="text" id="apellido" name="apellido" size="30"/></td>
    </tr>
    <tr>
      <td>Fecha de Nacimiento:</td>
      <td>
        <input type="text" id="date" name="date" size="2"/>/
        <input type="text" id="month" name="month" size="2"/>/
        <input type="text" id="year" name="year" size="2"/>
        (dd/mm/aa)
      </td>
    </tr>
  </table>
  <input type="submit" value="Guardar">
</form>
