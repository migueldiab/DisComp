/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dao.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    credencialJPA.create(credencial);
  }

  @Override
  public void edit(Credencial credencial) {
    try {
      credencialJPA.edit(credencial);
    } catch (NonexistentEntityException ex) {
      Logger.getLogger(CredencialFacade.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
      Logger.getLogger(CredencialFacade.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public void remove(Credencial credencial) {
    try {
      credencialJPA.destroy(credencial.getLogin());
    } catch (NonexistentEntityException ex) {
      Logger.getLogger(CredencialFacade.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public Credencial find(Object id) {
    return credencialJPA.findCredencial((String) id);
  }

  @Override
  public List<Credencial> findAll() {
    return credencialJPA.findCredencialEntities();
  }

  @Override
  public List<Credencial> findRange(int[] range) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public int count() {
    return credencialJPA.findCredencialEntities().size();
  }

}
