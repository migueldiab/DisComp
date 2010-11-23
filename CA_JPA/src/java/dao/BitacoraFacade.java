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
public class BitacoraFacade implements BitacoraFacadeLocal {
  @PersistenceContext(unitName = "CA_PU")
  private EntityManager em;

  private BitacoraJpaController bitacoraJPA = new BitacoraJpaController();

  protected EntityManager getEntityManager() {
    return em;
  }

  @Override
  public void create(Bitacora bitacora) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void edit(Bitacora bitacora) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void remove(Bitacora bitacora) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Bitacora find(Object id) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public List<Bitacora> findAll() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public List<Bitacora> findRange(int[] range) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public int count() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
