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
public class CredencialFacade implements CredencialFacadeLocal {
  @PersistenceContext(unitName = "CA_PU")
  private EntityManager em;

  private CredencialJpaController credencialJPA = new CredencialJpaController();

  protected EntityManager getEntityManager() {
    return em;
  }

  @Override
  public void create(Credencial credencial) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void edit(Credencial credencial) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void remove(Credencial credencial) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Credencial find(Object id) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public List<Credencial> findAll() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public List<Credencial> findRange(int[] range) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public int count() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
