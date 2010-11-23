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
public class CredencialJpaController {

  public CredencialJpaController() {
    emf = Persistence.createEntityManagerFactory("CA_PU");
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Credencial credencial) {
    EntityManager em = null;
    try {
      em = getEntityManager();
      Usuario usuario = credencial.getUsuario();
      if (usuario != null) {
        usuario = em.getReference(usuario.getClass(), usuario.getId());
        credencial.setUsuario(usuario);
      }
      em.persist(credencial);
      if (usuario != null) {
        Credencial oldCredencialOfUsuario = usuario.getCredencial();
        if (oldCredencialOfUsuario != null) {
          oldCredencialOfUsuario.setUsuario(null);
          oldCredencialOfUsuario = em.merge(oldCredencialOfUsuario);
        }
        usuario.setCredencial(credencial);
        usuario = em.merge(usuario);
      }
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Credencial credencial) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      Credencial persistentCredencial = em.find(Credencial.class, credencial.getLogin());
      Usuario usuarioOld = persistentCredencial.getUsuario();
      Usuario usuarioNew = credencial.getUsuario();
      if (usuarioNew != null) {
        usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
        credencial.setUsuario(usuarioNew);
      }
      credencial = em.merge(credencial);
      if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
        usuarioOld.setCredencial(null);
        usuarioOld = em.merge(usuarioOld);
      }
      if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
        Credencial oldCredencialOfUsuario = usuarioNew.getCredencial();
        if (oldCredencialOfUsuario != null) {
          oldCredencialOfUsuario.setUsuario(null);
          oldCredencialOfUsuario = em.merge(oldCredencialOfUsuario);
        }
        usuarioNew.setCredencial(credencial);
        usuarioNew = em.merge(usuarioNew);
      }
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        String id = credencial.getLogin();
        if (findCredencial(id) == null) {
          throw new NonexistentEntityException("The credencial with id " + id + " no longer exists.");
        }
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void destroy(String id) throws NonexistentEntityException {
    EntityManager em = null;
    try {
      em = getEntityManager();
      Credencial credencial;
      try {
        credencial = em.getReference(Credencial.class, id);
        credencial.getLogin();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The credencial with id " + id + " no longer exists.", enfe);
      }
      Usuario usuario = credencial.getUsuario();
      if (usuario != null) {
        usuario.setCredencial(null);
        usuario = em.merge(usuario);
      }
      em.remove(credencial);
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Credencial> findCredencialEntities() {
    return findCredencialEntities(true, -1, -1);
  }

  public List<Credencial> findCredencialEntities(int maxResults, int firstResult) {
    return findCredencialEntities(false, maxResults, firstResult);
  }

  private List<Credencial> findCredencialEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      Query q = em.createQuery("select object(o) from Credencial as o");
      if (!all) {
        q.setMaxResults(maxResults);
        q.setFirstResult(firstResult);
      }
      return q.getResultList();
    } finally {
      em.close();
    }
  }

  public Credencial findCredencial(String id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Credencial.class, id);
    } finally {
      em.close();
    }
  }

  public int getCredencialCount() {
    EntityManager em = getEntityManager();
    try {
      Query q = em.createQuery("select count(o) from Credencial as o");
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }

}
