package actions;

import edu.ort.discomp.framework.FrontController;
import edu.ort.discomp.framework.WebAction;
import dao.Usuario;
import dao.UsuarioFacadeLocal;
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
public class CrearUsuario extends WebAction {
  UsuarioFacadeLocal UsuarioFacade1 = lookupUsuarioFacadeLocal();

  @EJB
  private UsuarioFacadeLocal usuarioFacade;

  @Override
  public String execute
          (final FrontController servlet, final HttpServletRequest request, final HttpServletResponse response)
          throws ServletException {
    final Usuario unUsuario = new Usuario();
    unUsuario.setNombre("Miguel23");
    unUsuario.setApellido("Diab23");
    unUsuario.setId(32);
    usuarioFacade = lookupUsuarioFacadeLocal();
    usuarioFacade.create(unUsuario);
    List<Usuario> findUsuarioEntities = usuarioFacade.findAll();
    for (Usuario elem : findUsuarioEntities) {
      System.out.println(elem.getApellido());
    }

    return "OK!";
  }

  private UsuarioFacadeLocal lookupUsuarioFacadeLocal() {
    try {
      Context c = new InitialContext();
      return (UsuarioFacadeLocal) c.lookup("java:global/CA_App/CA_JPA/UsuarioFacade!dao.UsuarioFacadeLocal");
    } catch (NamingException ne) {
      Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
      throw new RuntimeException(ne);
    }
  }

}
