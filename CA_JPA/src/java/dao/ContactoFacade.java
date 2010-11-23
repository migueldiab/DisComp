/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author migueldiab
 */
@Stateless
public class ContactoFacade implements ContactoFacadeLocal {
  @PersistenceContext(unitName = "CA_PU")
  private EntityManager em;

  private ContactoJpaController contactoJPA = new ContactoJpaController();

  protected EntityManager getEntityManager() {
    return em;
  }

  @Override
  public void create(Contacto contacto) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void edit(Contacto contacto) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void remove(Contacto contacto) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Contacto find(Object id) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public List<Contacto> findAll() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public List<Contacto> findRange(int[] range) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public int count() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
