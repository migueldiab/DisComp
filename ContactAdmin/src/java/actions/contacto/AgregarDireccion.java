package actions.contacto;

import dao.Contacto;
import edu.ort.discomp.framework.FrontController;
import edu.ort.discomp.framework.WebAction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author migueldiab
 */
public class AgregarDireccion extends WebAction {

  @Override
  public String execute
          (final FrontController servlet, final HttpServletRequest request, final HttpServletResponse response)
          throws ServletException {

		try {
			request.setAttribute( "view", "/contacto/nuevaDireccion.jsp" );
      Contacto unContacto = ContactoContext.getInstace().findById(Integer.parseInt(request.getParameter("idContacto")));
      request.setAttribute("contacto", unContacto);
			forward( servlet, request, response, "/index.jsp" );
		}
		catch (Exception e) {
			throw new ServletException( "Failed to process request", e );
		}

    return "OK!";
  }
}
