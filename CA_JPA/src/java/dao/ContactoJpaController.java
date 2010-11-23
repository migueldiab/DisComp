/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dao.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author migueldiab
 */
public class ContactoJpaController {

  public ContactoJpaController() {
    emf = Persistence.createEntityManagerFactory("CA_PU");
  }
  private EntityManagerFactory emf = null;

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Contacto contacto) {
    if (contacto.getDirecciones() == null) {
      contacto.setDirecciones(new ArrayList<Direccion>());
    }
    EntityManager em = null;
    try {
      em = getEntityManager();
      Usuario usuario = contacto.getUsuario();
      if (usuario != null) {
        usuario = em.getReference(usuario.getClass(), usuario.getId());
        contacto.setUsuario(usuario);
      }
      List<Direccion> attachedDirecciones = new ArrayList<Direccion>();
      for (Direccion direccionesDireccionToAttach : contacto.getDirecciones()) {
        direccionesDireccionToAttach = em.getReference(direccionesDireccionToAttach.getClass(), direccionesDireccionToAttach.getId());
        attachedDirecciones.add(direccionesDireccionToAttach);
      }
      contacto.setDirecciones(attachedDirecciones);
      em.persist(contacto);
      if (usuario != null) {
        usuario.getContactos().add(contacto);
        usuario = em.merge(usuario);
      }
      for (Direccion direccionesDireccion : contacto.getDirecciones()) {
        Contacto oldContactoOfDireccionesDireccion = direccionesDireccion.getContacto();
        direccionesDireccion.setContacto(contacto);
        direccionesDireccion = em.merge(direccionesDireccion);
        if (oldContactoOfDireccionesDireccion != null) {
          oldContactoOfDireccionesDireccion.getDirecciones().remove(direccionesDireccion);
          oldContactoOfDireccionesDireccion = em.merge(oldContactoOfDireccionesDireccion);
        }
      }
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Contacto contacto) throws NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
      em = getEntityManager();
      Contacto persistentContacto = em.find(Contacto.class, contacto.getId());
      Usuario usuarioOld = persistentContacto.getUsuario();
      Usuario usuarioNew = contacto.getUsuario();
      List<Direccion> direccionesOld = persistentContacto.getDirecciones();
      List<Direccion> direccionesNew = contacto.getDirecciones();
      if (usuarioNew != null) {
        usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
        contacto.setUsuario(usuarioNew);
      }
      List<Direccion> attachedDireccionesNew = new ArrayList<Direccion>();
      for (Direccion direccionesNewDireccionToAttach : direccionesNew) {
        direccionesNewDireccionToAttach = em.getReference(direccionesNewDireccionToAttach.getClass(), direccionesNewDireccionToAttach.getId());
        attachedDireccionesNew.add(direccionesNewDireccionToAttach);
      }
      direccionesNew = attachedDireccionesNew;
      contacto.setDirecciones(direccionesNew);
      contacto = em.merge(contacto);
      if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
        usuarioOld.getContactos().remove(contacto);
        usuarioOld = em.merge(usuarioOld);
      }
      if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
        usuarioNew.getContactos().add(contacto);
        usuarioNew = em.merge(usuarioNew);
      }
      for (Direccion direccionesOldDireccion : direccionesOld) {
        if (!direccionesNew.contains(direccionesOldDireccion)) {
          direccionesOldDireccion.setContacto(null);
          direccionesOldDireccion = em.merge(direccionesOldDireccion);
        }
      }
      for (Direccion direccionesNewDireccion : direccionesNew) {
        if (!direccionesOld.contains(direccionesNewDireccion)) {
          Contacto oldContactoOfDireccionesNewDireccion = direccionesNewDireccion.getContacto();
          direccionesNewDireccion.setContacto(contacto);
          direccionesNewDireccion = em.merge(direccionesNewDireccion);
          if (oldContactoOfDireccionesNewDireccion != null && !oldContactoOfDireccionesNewDireccion.equals(contacto)) {
            oldContactoOfDireccionesNewDireccion.getDirecciones().remove(direccionesNewDireccion);
            oldContactoOfDireccionesNewDireccion = em.merge(oldContactoOfDireccionesNewDireccion);
          }
        }
      }
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        Integer id = contacto.getId();
        if (findContacto(id) == null) {
          throw new NonexistentEntityException("The contacto with id " + id + " no longer exists.");
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
      Contacto contacto;
      try {
        contacto = em.getReference(Contacto.class, id);
        contacto.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The contacto with id " + id + " no longer exists.", enfe);
      }
      Usuario usuario = contacto.getUsuario();
      if (usuario != null) {
        usuario.getContactos().remove(contacto);
        usuario = em.merge(usuario);
      }
      List<Direccion> direcciones = contacto.getDirecciones();
      for (Direccion direccionesDireccion : direcciones) {
        direccionesDireccion.setContacto(null);
        direccionesDireccion = em.merge(direccionesDireccion);
      }
      em.remove(contacto);
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Contacto> findContactoEntities() {
    return findContactoEntities(true, -1, -1);
  }

  public List<Contacto> findContactoEntities(int maxResults, int firstResult) {
    return findContactoEntities(false, maxResults, firstResult);
  }

  private List<Contacto> findContactoEntities(boolean all, int maxResults, int firstResult) {
    EntityManager em = getEntityManager();
    try {
      Query q = em.createQuery("select object(o) from Contacto as o");
      if (!all) {
        q.setMaxResults(maxResults);
        q.setFirstResult(firstResult);
      }
      return q.getResultList();
    } finally {
      em.close();
    }
  }

  public Contacto findContacto(Integer id) {
    EntityManager em = getEntityManager();
    try {
      return em.find(Contacto.class, id);
    } finally {
      em.close();
    }
  }

  public int getContactoCount() {
    EntityManager em = getEntityManager();
    try {
      Query q = em.createQuery("select count(o) from Contacto as o");
      return ((Long) q.getSingleResult()).intValue();
    } finally {
      em.close();
    }
  }

}
