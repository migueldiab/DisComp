package edu.ort.discomp.framework;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author migueldiab
 */
public class CommandFactory {

  /**
   * Mapa de acciones del framework.
   */
  private Map<String, Class<?>> actionMap = new HashMap<String, Class<?>>();

  public static final String HOME = "home";
  public static final String ERROR = "error";
  public static final String VIEW = "view";

  public CommandFactory() {
    actionMap.put(HOME, HomeWebAction.class);
    actionMap.put(ERROR, NotFound404WebAction.class);
  }

  void forwardByActionMap(FrontController servlet, String key, HttpServletRequest request, HttpServletResponse response)
          throws InstantiationException, IllegalAccessException, ServletException {
    final Class<?> webActionClass = (Class<?>) actionMap.get(key);
    final WebAction webAction = (WebAction) webActionClass.newInstance();
    webAction.execute(servlet, request, response);
  }

  String forwardByReflection(FrontController servlet, String type, HttpServletRequest request, HttpServletResponse response)
          throws ClassNotFoundException, InstantiationException, ServletException, IllegalAccessException {

    final ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
    final Class<?> webClass = contextClassLoader.loadClass(type);
    final WebAction webAction = (WebAction) webClass.newInstance();
    return webAction.execute(servlet, request, response);
    
  }

}
