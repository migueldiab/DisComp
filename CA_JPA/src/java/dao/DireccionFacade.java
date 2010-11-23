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
public class DireccionFacade implements DireccionFacadeLocal {
  @PersistenceContext(unitName = "CA_PU")
  private EntityManager em;

  private DireccionJpaController direccionJPA = new DireccionJpaController();

  protected EntityManager getEntityManager() {
    return em;
  }

  @Override
  public void create(Direccion direccion) {
    direccionJPA.create(direccion);
  }

  @Override
  public void edit(Direccion direccion) {
    try {
      direccionJPA.edit(direccion);
    } catch (NonexistentEntityException ex) {
      Logger.getLogger(DireccionFacade.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
      Logger.getLogger(DireccionFacade.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public void remove(Direccion direccion) {
    try {
      direccionJPA.destroy(direccion.getId());
    } catch (NonexistentEntityException ex) {
      Logger.getLogger(DireccionFacade.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public Direccion find(Object id) {
    return direccionJPA.findDireccion((Integer) id);
  }

  @Override
  public List<Direccion> findAll() {
    return direccionJPA.findDireccionEntities();
  }

  @Override
  public List<Direccion> findRange(int[] range) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public int count() {
    return direccionJPA.findDireccionEntities().size();
  }

  @Override
  public Direccion findById(int id) {
    return direccionJPA.findDireccion(id);
  }

}
