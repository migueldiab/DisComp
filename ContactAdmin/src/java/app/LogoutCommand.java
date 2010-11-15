/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package app;

import edu.ort.discomp.framework.CommandFactory;
import edu.ort.discomp.framework.FrontController;
import edu.ort.discomp.framework.WebAction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author migueldiab
 */
public class LogoutCommand extends WebAction {

  @Override
  public String execute(FrontController servlet, HttpServletRequest request, HttpServletResponse response) throws ServletException {
    return "ok";
  }

}
