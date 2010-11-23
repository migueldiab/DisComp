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
public interface DireccionFacadeLocal {

  void create(Direccion direccion);

  void edit(Direccion direccion);

  void remove(Direccion direccion);

  Direccion find(Object id);

  List<Direccion> findAll();

  List<Direccion> findRange(int[] range);

  int count();

}
