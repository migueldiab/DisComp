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
public class Editar extends WebAction {
  
  @Override
  public String execute
          (final FrontController servlet, final HttpServletRequest request, final HttpServletResponse response)
          throws ServletException {
    Contacto unContacto = null;
    if (request.getParameterMap().containsKey("id")) {
      unContacto = ContactoContext.getInstace().findById(Integer.parseInt(request.getParameter("id")));
    }
    if (unContacto==null) {
      forward( servlet, request, response, "/error.jsp" );
    } else {
      request.setAttribute( "contacto", unContacto);
      request.setAttribute( "view", "/contacto/editar.jsp" );
      forward( servlet, request, response, "/index.jsp" );
    }
    return "OK!";
  }

}
