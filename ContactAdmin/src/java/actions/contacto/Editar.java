package actions.contacto;

import dao.Usuario;
import dao.UsuarioFacade;
import edu.ort.discomp.framework.FrontController;
import edu.ort.discomp.framework.WebAction;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
    Usuario unUsuario = null;
    if (request.getParameterMap().containsKey("id")) {
      unUsuario = ContactoContext.getInstace().findById(Integer.parseInt(request.getParameter("id")));
    }
    if (unUsuario==null) {
      forward( servlet, request, response, "/error.jsp" );
    } else {
      request.setAttribute( "usuario", unUsuario);
      request.setAttribute( "view", "/usuario/editar.jsp" );
      forward( servlet, request, response, "/index.jsp" );
    }
    return "OK!";
  }

}
