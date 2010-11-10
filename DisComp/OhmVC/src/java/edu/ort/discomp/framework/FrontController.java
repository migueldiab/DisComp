package edu.ort.discomp.framework;

import edu.ort.discomp.xml.MvcXMLReader;
import edu.ort.discomp.xml.jaxb.Command;
import edu.ort.discomp.xml.jaxb.CommandMappings;
import edu.ort.discomp.xml.jaxb.Forward;
import edu.ort.discomp.xml.jaxb.MvcConfig;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {

  @Override
  public void init() throws ServletException {
    super.init();
    MvcXMLReader.loadMvcConfig(getServletContext().getResourceAsStream("/WEB-INF/mvc.xml"));
  }

  /**
   * Method 'FrontController'.
   */
  public FrontController() {
    
  }

  public void doRedirect(final HttpServletRequest request, final HttpServletResponse response) throws ServletException {
    
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
      processFrontControllerRequest(request, response);
    } catch (InstantiationException ex) {
      Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
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

  private void processFrontControllerRequest(final HttpServletRequest request, final HttpServletResponse response)
          throws InstantiationException, ServletException, IllegalAccessException {
    String servletPath = request.getRequestURI();
    String contextPath = getServletContext().getContextPath();
    
    String servletName = getServletName();
    String pathInfo = request.getPathInfo();
    System.out.println("1" + servletPath);
    System.out.println("2" + contextPath);
    System.out.println("3" + servletName);
    System.out.println("4" + pathInfo);

    String commandCalled = servletPath.replaceAll(contextPath, "");
    MvcConfig mvcConfig = MvcXMLReader.getMvcConfig();
    CommandMappings commandMappings = mvcConfig.getCommandMappings();
    for (Command command : commandMappings.getCommand()) {
      if (command.getPath().equals(commandCalled)) {
        if (command.getUrl() != null) {
          try {
            forwardByURL(command.getUrl(), request, response);
          } catch (IOException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
          }
        } else if (command.getType() != null) {
          try {
            forwardByType(command, request, response);
          } catch (ClassNotFoundException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
      }
    }
    new CommandFactory().forwardByActionMap("error", request, response);
    
  }

  private void forwardByURL(String url, HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    request.setAttribute("view", url);
    ServletContext ctx = getServletContext();
    RequestDispatcher dispatcher = ctx.getRequestDispatcher("/index.jsp");
    dispatcher.forward(request, response);
  }

  private void forwardByType(Command command, HttpServletRequest request, HttpServletResponse response)
          throws ClassNotFoundException, InstantiationException, ServletException, IllegalAccessException {
    final String responseForward = new CommandFactory().forwardByReflection(command.getType(), request, response);
    if (responseForward != null && !responseForward.isEmpty()) {
      for (Forward forward : command.getForward()) {
        if (forward.getName().equals(responseForward) && forward.getPath() != null) {
          try {
            forwardByURL(forward.getPath(), request, response);
          } catch (IOException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
      }
    }
  }
}
