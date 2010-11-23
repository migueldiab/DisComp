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
public class BitacoraJpaController {

  public BitacoraJpaController() {
    emf = Persistence.createEntityManagerFactory("CA_PU");
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Bitacora bitacora) {
    EntityManager em = null;
    try {
      em = getEntityManager();
      em.persist(bitacora);
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Bitacora bitacora) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      bitacora = em.merge(bitacora);
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Integer id = bitacora.getId();
        if (findBitacora(id) == null) {
          throw new NonexistentEntityException("The bitacora with id " + id + " no longer exists.");
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
      Bitacora bitacora;
      try {
        bitacora = em.getReference(Bitacora.class, id);
        bitacora.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The bitacora with id " + id + " no longer exists.", enfe);
      }
      em.remove(bitacora);
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Bitacora> findBitacoraEntities() {
    return findBitacoraEntities(true, -1, -1);
  }

  public List<Bitacora> findBitacoraEntities(int maxResults, int firstResult) {
    return findBitacoraEntities(false, maxResults, firstResult);
  }

  private List<Bitacora> findBitacoraEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      Query q = em.createQuery("select object(o) from Bitacora as o");
      if (!all) {
        q.setMaxResults(maxResults);
        q.setFirstResult(firstResult);
      }
      return q.getResultList();
    } finally {
      em.close();
    }
  }

  public Bitacora findBitacora(Integer id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Bitacora.class, id);
    } finally {
      em.close();
    }
  }

  public int getBitacoraCount() {
    EntityManager em = getEntityManager();
    try {
      Query q = em.createQuery("select count(o) from Bitacora as o");
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }

}
