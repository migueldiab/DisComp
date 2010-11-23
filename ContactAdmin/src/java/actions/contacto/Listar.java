package actions.contacto;

import edu.ort.discomp.framework.FrontController;
import edu.ort.discomp.framework.WebAction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author migueldiab
 */
public class Listar extends WebAction {

  @Override
  public String execute
          (final FrontController servlet, final HttpServletRequest request, final HttpServletResponse response)
          throws ServletException {

		try {
			request.setAttribute("usuarios", ContactoContext.getInstace().findAll());
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
