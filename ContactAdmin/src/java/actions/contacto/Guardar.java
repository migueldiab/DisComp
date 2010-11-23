package actions.contacto;

import dao.Usuario;
import edu.ort.discomp.framework.FrontController;
import edu.ort.discomp.framework.WebAction;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author migueldiab
 */
public class Guardar extends WebAction {

  @Override
  public String execute
          (final FrontController servlet, final HttpServletRequest request, final HttpServletResponse response)
          throws ServletException {

		try {
      Usuario unUsuario = new Usuario();
      if (request.getParameterMap().containsKey("id")) {
        unUsuario = ContactoContext.getInstace().findById(Integer.parseInt(request.getParameter("id")));
      }
      final String nombre  =  request.getParameter("nombre");
      final String apellido  =  request.getParameter("apellido");
      final int year  =  Integer.parseInt(request.getParameter("year"));
      final int month  =  Integer.parseInt(request.getParameter("month"));
      final int date  =  Integer.parseInt(request.getParameter("date"));
      Calendar fdn = Calendar.getInstance();
      fdn.set(year, month, date);
      unUsuario.setNombre(nombre);
      unUsuario.setApellido(apellido);
      unUsuario.setFechaDeNacimiento(fdn.getTime());
      if (request.getParameterMap().containsKey("id")) {
        ContactoContext.getInstace().edit(unUsuario);
      } else {
        ContactoContext.getInstace().create(unUsuario);
      }

			request.setAttribute( "usuarios", ContactoContext.getInstace().findAll() );

			// forward request on to the appropriate JSP page to display the results
			request.setAttribute( "view", "/usuario/listar.jsp" );
			forward( servlet, request, response, "/index.jsp" );
		}
		catch (Exception e) {
			throw new ServletException( "Failed to process request", e );
		}

    return "OK!";
  }
}
