/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author migueldiab
 */
@Local
public interface UsuarioFacadeLocal {

  void create(Usuario usuario);

  void edit(Usuario usuario);

  void remove(Usuario usuario);

  Usuario find(Object id);

  List<Usuario> findAll();

  List<Usuario> findRange(int[] range);

  int count();

}
