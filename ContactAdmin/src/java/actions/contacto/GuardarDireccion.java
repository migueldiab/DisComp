package actions.contacto;

import actions.direccion.*;
import actions.contacto.ContactoContext;
import dao.Contacto;
import dao.Direccion;
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
public class GuardarDireccion extends WebAction {

  @Override
  public String execute
          (final FrontController servlet, final HttpServletRequest request, final HttpServletResponse response)
          throws ServletException {

		try {
      Direccion unDireccion = new Direccion();
      Contacto unContacto = ContactoContext.getInstace().findById(Integer.parseInt(request.getParameter("idContacto")));
      unDireccion.setContacto(unContacto);
      if (request.getParameterMap().containsKey("id")) {
        unDireccion = DireccionContext.getInstace().findById(Integer.parseInt(request.getParameter("id")));
      }
      unDireccion.setCalle(request.getParameter("calle"));
      unDireccion.setNumero(request.getParameter("numero"));
      if (request.getParameterMap().containsKey("id")) {
        DireccionContext.getInstace().edit(unDireccion);
      } else {
        DireccionContext.getInstace().create(unDireccion);
      }

			request.setAttribute("contacto", unContacto);

			// forward request on to the appropriate JSP page to display the results
			request.setAttribute( "view", "/contacto/editar.jsp" );
			forward( servlet, request, response, "/index.jsp" );
		}
		catch (Exception e) {
			throw new ServletException( "Failed to process request", e );
		}

    return "OK!";
  }
}
