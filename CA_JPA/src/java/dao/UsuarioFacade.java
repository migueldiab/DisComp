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
public class UsuarioFacade implements UsuarioFacadeLocal {
  @PersistenceContext(unitName = "CA_PU")
  private EntityManager em;

  private UsuarioJpaController usuarioJPA = new UsuarioJpaController();

  protected EntityManager getEntityManager() {
    return em;
  }

  @Override
  public void remove(Usuario usuario) {
    try {
      usuarioJPA.destroy(usuario.getId());
    } catch (NonexistentEntityException ex) {
      Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public Usuario find(Object id) {
    return usuarioJPA.findUsuario((Integer) id);
  }

  @Override
  public List<Usuario> findAll() {
    return usuarioJPA.findUsuarioEntities();
  }

  @Override
  public List<Usuario> findRange(int[] range) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public int count() {
    return usuarioJPA.findUsuarioEntities().size();
  }

  @Override
  public void create(Usuario usuario) {
    usuarioJPA.create(usuario);
  }

  @Override
  public void edit(Usuario usuario) {
    try {
      usuarioJPA.edit(usuario);
    } catch (NonexistentEntityException ex) {
      Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
      Logger.getLogger(UsuarioFacade.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public Usuario findById(Integer id) {
    return usuarioJPA.findUsuario(id);
  }

}
