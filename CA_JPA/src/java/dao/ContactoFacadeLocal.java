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
public interface ContactoFacadeLocal {

  void create(Contacto contacto);

  void edit(Contacto contacto);

  void remove(Contacto contacto);

  Contacto find(Object id);

  List<Contacto> findAll();

  List<Contacto> findRange(int[] range);

  int count();

  public Contacto findById(int parseInt);

}
