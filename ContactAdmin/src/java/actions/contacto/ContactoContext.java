/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package actions.contacto;

import dao.ContactoFacade;
import dao.ContactoFacadeLocal;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author migueldiab
 */
public class ContactoContext {

  private static ContactoFacadeLocal contactoFacade;

  public static ContactoFacadeLocal getInstace() {
     if (contactoFacade==null) {
       contactoFacade = lookupContactoFacade();
     }
     return contactoFacade;
  }
  
  private static ContactoFacadeLocal lookupContactoFacade() {
    try {
      Context c = new InitialContext();
      return (ContactoFacadeLocal) c.lookup("java:global/CA_App/CA_JPA/ContactoFacade!dao.ContactoFacadeLocal");
    } catch (NamingException ne) {
      throw new RuntimeException(ne);
    }
  }
}
