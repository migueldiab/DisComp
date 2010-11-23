/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dao.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author migueldiab
 */
public class DireccionJpaController {

  public DireccionJpaController() {
    emf = Persistence.createEntityManagerFactory("CA_PU");
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Direccion direccion) {
    EntityManager em = null;
    try {
      em = getEntityManager();
      Contacto contacto = direccion.getContacto();
      if (contacto != null) {
        contacto = em.getReference(contacto.getClass(), contacto.getId());
        direccion.setContacto(contacto);
      }
      em.persist(direccion);
      if (contacto != null) {
        contacto.getDirecciones().add(direccion);
        contacto = em.merge(contacto);
      }
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Direccion direccion) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      Direccion persistentDireccion = em.find(Direccion.class, direccion.getId());
      Contacto contactoOld = persistentDireccion.getContacto();
      Contacto contactoNew = direccion.getContacto();
      if (contactoNew != null) {
        contactoNew = em.getReference(contactoNew.getClass(), contactoNew.getId());
        direccion.setContacto(contactoNew);
      }
      direccion = em.merge(direccion);
      if (contactoOld != null && !contactoOld.equals(contactoNew)) {
        contactoOld.getDirecciones().remove(direccion);
        contactoOld = em.merge(contactoOld);
      }
      if (contactoNew != null && !contactoNew.equals(contactoOld)) {
        contactoNew.getDirecciones().add(direccion);
        contactoNew = em.merge(contactoNew);
      }
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Integer id = direccion.getId();
        if (findDireccion(id) == null) {
          throw new NonexistentEntityException("The direccion with id " + id + " no longer exists.");
        }
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void destroy(Integer id) throws NonexistentEntityException {
    EntityManager em = null;
    try {
      em = getEntityManager();
      Direccion direccion;
      try {
        direccion = em.getReference(Direccion.class, id);
        direccion.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The direccion with id " + id + " no longer exists.", enfe);
      }
      Contacto contacto = direccion.getContacto();
      if (contacto != null) {
        contacto.getDirecciones().remove(direccion);
        contacto = em.merge(contacto);
      }
      em.remove(direccion);
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Direccion> findDireccionEntities() {
    return findDireccionEntities(true, -1, -1);
  }

  public List<Direccion> findDireccionEntities(int maxResults, int firstResult) {
    return findDireccionEntities(false, maxResults, firstResult);
  }

  private List<Direccion> findDireccionEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      Query q = em.createQuery("select object(o) from Direccion as o");
      if (!all) {
        q.setMaxResults(maxResults);
        q.setFirstResult(firstResult);
      }
      return q.getResultList();
    } finally {
      em.close();
    }
  }

  public Direccion findDireccion(Integer id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Direccion.class, id);
    } finally {
      em.close();
    }
  }

  public int getDireccionCount() {
    EntityManager em = getEntityManager();
    try {
      Query q = em.createQuery("select count(o) from Direccion as o");
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }

}
