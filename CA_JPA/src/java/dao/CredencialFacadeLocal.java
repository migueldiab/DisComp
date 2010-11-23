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
public interface CredencialFacadeLocal {

  void create(Credencial credencial);

  void edit(Credencial credencial);

  void remove(Credencial credencial);

  Credencial find(Object id);

  List<Credencial> findAll();

  List<Credencial> findRange(int[] range);

  int count();

}
