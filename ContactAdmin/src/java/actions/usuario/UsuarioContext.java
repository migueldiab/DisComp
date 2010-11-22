/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package actions.usuario;

import dao.UsuarioFacade;
import dao.UsuarioFacadeLocal;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author migueldiab
 */
public class UsuarioContext {

  private static UsuarioFacadeLocal usuarioFacade;

  public static UsuarioFacadeLocal getInstace() {
     if (usuarioFacade==null) {
       usuarioFacade = lookupUsuarioFacade();
     }
     return usuarioFacade;
  }
  
  private static UsuarioFacadeLocal lookupUsuarioFacade() {
    try {
      Context c = new InitialContext();
      return (UsuarioFacadeLocal) c.lookup("java:global/CA_App/CA_JPA/UsuarioFacade!dao.UsuarioFacadeLocal");
    } catch (NamingException ne) {
      throw new RuntimeException(ne);
    }
  }
}
