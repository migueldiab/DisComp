package actions;

import dao.Usuario;
import edu.ort.discomp.framework.FrontController;
import edu.ort.discomp.framework.WebAction;
import ejb.UsuarioJpaController;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author migueldiab
 */
public class CrearUsuario extends WebAction {

  @Override
  public String execute
          (final FrontController servlet, final HttpServletRequest request, final HttpServletResponse response)
          throws ServletException {

    final Usuario unUsuario = new Usuario();
    unUsuario.setNombre("Miguel");
    unUsuario.setApellido("Diab");
    unUsuario.setId(1);

    UsuarioJpaController usuarioJPA = new UsuarioJpaController();
    usuarioJPA.create(unUsuario);
    

    return "OK!";
  }

}
