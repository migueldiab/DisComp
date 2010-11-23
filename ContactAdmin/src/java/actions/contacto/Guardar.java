package actions.contacto;

import dao.Contacto;
import edu.ort.discomp.framework.FrontController;
import edu.ort.discomp.framework.WebAction;
import java.util.Calendar;
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
      Contacto unContacto = new Contacto();
      if (request.getParameterMap().containsKey("id")) {
        unContacto = ContactoContext.getInstace().findById(Integer.parseInt(request.getParameter("id")));
      }
      final String nombre  =  request.getParameter("nombre");
      final String apellido  =  request.getParameter("apellido");
      unContacto.setNombre(nombre);
      unContacto.setApellido(apellido);
      if (request.getParameterMap().containsKey("id")) {
        ContactoContext.getInstace().edit(unContacto);
      } else {
        ContactoContext.getInstace().create(unContacto);
      }

			request.setAttribute( "contactos", ContactoContext.getInstace().findAll() );

			// forward request on to the appropriate JSP page to display the results
			request.setAttribute( "view", "/contacto/listar.jsp" );
			forward( servlet, request, response, "/index.jsp" );
		}
		catch (Exception e) {
			throw new ServletException( "Failed to process request", e );
		}

    return "OK!";
  }
}
