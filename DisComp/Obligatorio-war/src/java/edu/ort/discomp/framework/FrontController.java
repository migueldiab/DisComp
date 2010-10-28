package edu.ort.discomp.framework;

import edu.ort.discomp.xml.MvcXMLReader;
import edu.ort.discomp.xml.mvc.Command;
import edu.ort.discomp.xml.mvc.CommandMappings;
import edu.ort.discomp.xml.mvc.MvcConfig;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

public class FrontController extends HttpServlet {
  /**
   * Mapa de acciones del framework.
   */
  private Map<String, Class<?>> actionMap;

  /**
   * Method 'FrontController'.
   */
  public FrontController() {

    MvcXMLReader.loadMvcConfig();
    MvcConfig mvcConfig = MvcXMLReader.getMvcConfig();
    CommandMappings commandMappings = mvcConfig.getCommandMappings();
    actionMap = new HashMap<String, Class<?>>();
    for (Command command : commandMappings.getCommand()) {
      try {
        actionMap.put(command.getPath(), Class.forName(command.getForwardPath()));
      } catch (ClassNotFoundException ex) {
        Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    actionMap.put("/Bienvenido", NotFound404WebAction.class);
    actionMap.put("home", HomeWebAction.class);
    
  }

  public void doRedirect(final HttpServletRequest request, final HttpServletResponse response) throws ServletException {
    try {
      // get the action parameter
      final String action = request.getParameter("action");
      if (action == null) {
        Class<?> webActionClass = (Class<?>) actionMap.get("home");
        WebAction webAction = (WebAction) webActionClass.newInstance();
        webAction.execute(this, request, response);
      }
      // create the appropriate WebAction class
      Class<?> webActionClass = (Class<?>) actionMap.get(action);
      if (webActionClass == null) {
        webActionClass = (Class<?>) actionMap.get("home");
        WebAction webAction = (WebAction) webActionClass.newInstance();
        webAction.execute(this, request, response);
      }
      // instantiate the action class and execute it
      if (WebAdmin.usuarioAutenticado(request)) {
        WebAction webAction = (WebAction) webActionClass.newInstance();
        webAction.execute(this, request, response);
      } else {
        request.setAttribute("action", action);
        webActionClass = (Class<?>) actionMap.get("login");
        WebAction webAction = (WebAction) webActionClass.newInstance();
        webAction.execute(this, request, response);
      }
    }
    catch (ServletException e) {
      throw e;
    }
    catch (Exception e) {
      throw new ServletException("Failed to process request:" + e.getMessage(), e);
    }
  }

  /**
   * Method 'doPost'
   *
   * @param request
   * @param response
   * @throws ServletException
   */
  @Override
  public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException {
    try {      
      // get the action parameter
      String action = request.getParameter("action");
      if (action == null) {
        Class webActionClass = (Class<?>) actionMap.get("error");
        WebAction webAction = (WebAction) webActionClass.newInstance();
        webAction.execute(this, request, response);
        throw new ServletException("Parameter 'action' is required");        
      }
      // create the appropriate WebAction class
      Class<?> webActionClass = (Class<?>) actionMap.get(action);
      if (webActionClass == null) {
        webActionClass = (Class<?>) actionMap.get("error");
        WebAction webAction = (WebAction) webActionClass.newInstance();
        webAction.execute(this, request, response);
        throw new ServletException("Invalid action '" + action + "'");
      }
      // instantiate the action class and execute it
      if (WebAdmin.usuarioAutenticado(request)) {
        WebAction webAction = (WebAction) webActionClass.newInstance();
        webAction.execute(this, request, response);
      } else {
        request.setAttribute("action", action);
        webActionClass = (Class<?>) actionMap.get("login");
        WebAction webAction = (WebAction) webActionClass.newInstance();
        webAction.execute(this, request, response);
      }
    } catch (ServletException e) {
      throw e;
    } catch (Exception e) {
      throw new ServletException("Failed to process request:" + e.getMessage(), e);
    }
  }

  /**
   * Redirecciona toda llamada por GET al mismo maejador de POST
   *
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @throws ServletException Exception
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
    doPost(request, response);
  }

}
