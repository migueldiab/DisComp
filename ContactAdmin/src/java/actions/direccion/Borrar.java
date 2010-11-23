package actions.direccion;

import dao.Direccion;
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
        final Direccion unDireccion = DireccionContext.getInstace().findById(Integer.parseInt(request.getParameter("id")));
        DireccionContext.getInstace().remove(unDireccion);

        request.setAttribute( "direcciones", DireccionContext.getInstace().findAll() );

        // forward request on to the appropriate JSP page to display the results
        request.setAttribute( "view", "/direccion/listar.jsp" );
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
