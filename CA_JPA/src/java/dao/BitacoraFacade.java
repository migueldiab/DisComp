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
public class BitacoraFacade implements BitacoraFacadeLocal {
  @PersistenceContext(unitName = "CA_PU")
  private EntityManager em;

  private BitacoraJpaController bitacoraJPA = new BitacoraJpaController();

  protected EntityManager getEntityManager() {
    return em;
  }

  @Override
  public void create(Bitacora bitacora) {
    bitacoraJPA.create(bitacora);
  }

  @Override
  public void edit(Bitacora bitacora) {
    try {
      bitacoraJPA.edit(bitacora);
    } catch (NonexistentEntityException ex) {
      Logger.getLogger(BitacoraFacade.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
      Logger.getLogger(BitacoraFacade.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public void remove(Bitacora bitacora) {
    try {
      bitacoraJPA.destroy(bitacora.getId());
    } catch (NonexistentEntityException ex) {
      Logger.getLogger(BitacoraFacade.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public Bitacora find(Object id) {
    return bitacoraJPA.findBitacora((Integer) id);
  }

  @Override
  public List<Bitacora> findAll() {
    return bitacoraJPA.findBitacoraEntities();
  }

  @Override
  public List<Bitacora> findRange(int[] range) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public int count() {
    return bitacoraJPA.findBitacoraEntities().size();
  }

}
