/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author migueldiab
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {
  @PersistenceContext(unitName = "CA_PU")
  private EntityManager em;

  protected EntityManager getEntityManager() {
    return em;
  }

  public UsuarioFacade() {
    super(Usuario.class);
  }

}
