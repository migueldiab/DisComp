/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import dao.Usuario;
import ejb.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import dao.Credencial;
import dao.Contacto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author migueldiab
 */
@Stateless
public class UsuarioJpaController {

    public UsuarioJpaController() {
        emf = Persistence.createEntityManagerFactory("CA_PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getContactos() == null) {
            usuario.setContactos(new ArrayList<Contacto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Credencial credencial = usuario.getCredencial();
            if (credencial != null) {
                credencial = em.getReference(credencial.getClass(), credencial.getLogin());
                usuario.setCredencial(credencial);
            }
            List<Contacto> attachedContactos = new ArrayList<Contacto>();
            for (Contacto contactosContactoToAttach : usuario.getContactos()) {
                contactosContactoToAttach = em.getReference(contactosContactoToAttach.getClass(), contactosContactoToAttach.getId());
                attachedContactos.add(contactosContactoToAttach);
            }
            usuario.setContactos(attachedContactos);
            em.persist(usuario);
            if (credencial != null) {
                Usuario oldUsuarioOfCredencial = credencial.getUsuario();
                if (oldUsuarioOfCredencial != null) {
                    oldUsuarioOfCredencial.setCredencial(null);
                    oldUsuarioOfCredencial = em.merge(oldUsuarioOfCredencial);
                }
                credencial.setUsuario(usuario);
                credencial = em.merge(credencial);
            }
            for (Contacto contactosContacto : usuario.getContactos()) {
                Usuario oldUsuarioOfContactosContacto = contactosContacto.getUsuario();
                contactosContacto.setUsuario(usuario);
                contactosContacto = em.merge(contactosContacto);
                if (oldUsuarioOfContactosContacto != null) {
                    oldUsuarioOfContactosContacto.getContactos().remove(contactosContacto);
                    oldUsuarioOfContactosContacto = em.merge(oldUsuarioOfContactosContacto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId());
            Credencial credencialOld = persistentUsuario.getCredencial();
            Credencial credencialNew = usuario.getCredencial();
            List<Contacto> contactosOld = persistentUsuario.getContactos();
            List<Contacto> contactosNew = usuario.getContactos();
            if (credencialNew != null) {
                credencialNew = em.getReference(credencialNew.getClass(), credencialNew.getLogin());
                usuario.setCredencial(credencialNew);
            }
            List<Contacto> attachedContactosNew = new ArrayList<Contacto>();
            for (Contacto contactosNewContactoToAttach : contactosNew) {
                contactosNewContactoToAttach = em.getReference(contactosNewContactoToAttach.getClass(), contactosNewContactoToAttach.getId());
                attachedContactosNew.add(contactosNewContactoToAttach);
            }
            contactosNew = attachedContactosNew;
            usuario.setContactos(contactosNew);
            usuario = em.merge(usuario);
            if (credencialOld != null && !credencialOld.equals(credencialNew)) {
                credencialOld.setUsuario(null);
                credencialOld = em.merge(credencialOld);
            }
            if (credencialNew != null && !credencialNew.equals(credencialOld)) {
                Usuario oldUsuarioOfCredencial = credencialNew.getUsuario();
                if (oldUsuarioOfCredencial != null) {
                    oldUsuarioOfCredencial.setCredencial(null);
                    oldUsuarioOfCredencial = em.merge(oldUsuarioOfCredencial);
                }
                credencialNew.setUsuario(usuario);
                credencialNew = em.merge(credencialNew);
            }
            for (Contacto contactosOldContacto : contactosOld) {
                if (!contactosNew.contains(contactosOldContacto)) {
                    contactosOldContacto.setUsuario(null);
                    contactosOldContacto = em.merge(contactosOldContacto);
                }
            }
            for (Contacto contactosNewContacto : contactosNew) {
                if (!contactosOld.contains(contactosNewContacto)) {
                    Usuario oldUsuarioOfContactosNewContacto = contactosNewContacto.getUsuario();
                    contactosNewContacto.setUsuario(usuario);
                    contactosNewContacto = em.merge(contactosNewContacto);
                    if (oldUsuarioOfContactosNewContacto != null && !oldUsuarioOfContactosNewContacto.equals(usuario)) {
                        oldUsuarioOfContactosNewContacto.getContactos().remove(contactosNewContacto);
                        oldUsuarioOfContactosNewContacto = em.merge(oldUsuarioOfContactosNewContacto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Credencial credencial = usuario.getCredencial();
            if (credencial != null) {
                credencial.setUsuario(null);
                credencial = em.merge(credencial);
            }
            List<Contacto> contactos = usuario.getContactos();
            for (Contacto contactosContacto : contactos) {
                contactosContacto.setUsuario(null);
                contactosContacto = em.merge(contactosContacto);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Usuario as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Usuario as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
