/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package actions.direccion;

import dao.DireccionFacadeLocal;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author migueldiab
 */
public class DireccionContext {

  private static DireccionFacadeLocal direccionFacade;

  public static DireccionFacadeLocal getInstace() {
     if (direccionFacade==null) {
       direccionFacade = lookupDireccionFacade();
     }
     return direccionFacade;
  }
  
  private static DireccionFacadeLocal lookupDireccionFacade() {
    try {
      Context c = new InitialContext();
      return (DireccionFacadeLocal) c.lookup("java:global/CA_App/CA_JPA/DireccionFacade!dao.DireccionFacadeLocal");
    } catch (NamingException ne) {
      throw new RuntimeException(ne);
    }
  }
}
