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
public class ContactoFacade implements ContactoFacadeLocal {
  @PersistenceContext(unitName = "CA_PU")
  private EntityManager em;

  private ContactoJpaController contactoJPA = new ContactoJpaController();

  protected EntityManager getEntityManager() {
    return em;
  }

  @Override
  public void create(Contacto contacto) {
    contactoJPA.create(contacto);
  }

  @Override
  public void edit(Contacto contacto) {
    try {
      contactoJPA.edit(contacto);
    } catch (NonexistentEntityException ex) {
      Logger.getLogger(ContactoFacade.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
      Logger.getLogger(ContactoFacade.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public void remove(Contacto contacto) {
    try {
      contactoJPA.destroy(contacto.getId());
    } catch (NonexistentEntityException ex) {
      Logger.getLogger(ContactoFacade.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public Contacto find(Object id) {
    return contactoJPA.findContacto((Integer) id);
  }

  @Override
  public List<Contacto> findAll() {
    return contactoJPA.findContactoEntities();
  }

  @Override
  public List<Contacto> findRange(int[] range) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public int count() {
    return contactoJPA.findContactoEntities().size();
  }

  @Override
  public Contacto findById(int id) {
    return contactoJPA.findContacto(id);
  }

}
