package actions.usuario;

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
//    final Usuario unUsuario = new Usuario();
//    unUsuario.setNombre("Miguelius");
//    unUsuario.setApellido("D");
//    unUsuario.setId(7);
    List<Usuario> findUsuarioEntities = UsuarioContext.getInstace().findAll();
    for (Usuario elem : findUsuarioEntities) {
      System.out.println(elem.getApellido());
    }

    return "OK!";
  }

}
