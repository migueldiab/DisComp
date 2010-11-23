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
public interface BitacoraFacadeLocal {

  void create(Bitacora bitacora);

  void edit(Bitacora bitacora);

  void remove(Bitacora bitacora);

  Bitacora find(Object id);

  List<Bitacora> findAll();

  List<Bitacora> findRange(int[] range);

  int count();

}
