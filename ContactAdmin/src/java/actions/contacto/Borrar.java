package actions.contacto;

import dao.Contacto;
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
public class Borrar extends WebAction {

  @Override
  public String execute
          (final FrontController servlet, final HttpServletRequest request, final HttpServletResponse response)
          throws ServletException {

		try {
      if (request.getParameterMap().containsKey("id")) {
        final Contacto unContacto = ContactoContext.getInstace().findById(Integer.parseInt(request.getParameter("id")));
        ContactoContext.getInstace().remove(unContacto);

        request.setAttribute( "contactos", ContactoContext.getInstace().findAll() );

        // forward request on to the appropriate JSP page to display the results
        request.setAttribute( "view", "/contacto/listar.jsp" );
        forward( servlet, request, response, "/index.jsp" );
      } else {
        forward( servlet, request, response, "/error.jsp" );
      }
		}
		catch (Exception e) {
			throw new ServletException( "Failed to process request", e );
		}

    return "OK!";
  }
}
