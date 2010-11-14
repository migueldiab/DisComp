/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ort.discomp.framework;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author migueldiab
 */
public class CommandFactory extends HttpServlet {

  /**
   * Mapa de acciones del framework.
   */
  private Map<String, Class<?>> actionMap = new HashMap<String, Class<?>>();

  @Override
  public void init() throws ServletException {
    super.init();
    actionMap.put("home", HomeWebAction.class);
    actionMap.put("error", NotFound404WebAction.class);
  }


  protected String forwardByReflection(String type, HttpServletRequest request, HttpServletResponse response)
          throws ClassNotFoundException, InstantiationException, ServletException, IllegalAccessException {
    final Class<?> webActionClass = (Class<?>) Class.forName(type);
    final WebAction webAction = (WebAction) webActionClass.newInstance();
    return webAction.execute(this, request, response);
  }

  void forwardByActionMap(String key, HttpServletRequest request, HttpServletResponse response)
          throws InstantiationException, IllegalAccessException, ServletException {
    final Class<?> webActionClass = (Class<?>) actionMap.get(key);
    final WebAction webAction = (WebAction) webActionClass.newInstance();
    webAction.execute(this, request, response);
  }

}
