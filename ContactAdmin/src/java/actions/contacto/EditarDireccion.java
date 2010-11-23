package actions.contacto;

import actions.direccion.*;
import dao.Direccion;
import edu.ort.discomp.framework.FrontController;
import edu.ort.discomp.framework.WebAction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author migueldiab
 */
public class EditarDireccion extends WebAction {
  
  @Override
  public String execute
          (final FrontController servlet, final HttpServletRequest request, final HttpServletResponse response)
          throws ServletException {
    Direccion unDireccion = null;
    if (request.getParameterMap().containsKey("id")) {
      unDireccion = DireccionContext.getInstace().findById(Integer.parseInt(request.getParameter("id")));
    }
    if (unDireccion==null) {
      forward( servlet, request, response, "/error.jsp" );
    } else {
      request.setAttribute( "direccion", unDireccion);
      request.setAttribute( "view", "/contacto/editarDireccion.jsp" );
      forward( servlet, request, response, "/index.jsp" );
    }
    return "OK!";
  }

}
