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
public class DireccionFacade implements DireccionFacadeLocal {
  @PersistenceContext(unitName = "CA_PU")
  private EntityManager em;

  private DireccionJpaController direccionJPA = new DireccionJpaController();

  protected EntityManager getEntityManager() {
    return em;
  }

  @Override
  public void create(Direccion direccion) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void edit(Direccion direccion) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void remove(Direccion direccion) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Direccion find(Object id) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public List<Direccion> findAll() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public List<Direccion> findRange(int[] range) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public int count() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
