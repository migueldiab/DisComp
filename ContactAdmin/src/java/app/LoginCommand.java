package app;

import edu.ort.discomp.framework.FrontController;
import edu.ort.discomp.framework.WebAction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author migueldiab
 */
public class LoginCommand extends WebAction {

  @Override
  public String execute(final FrontController servlet, final HttpServletRequest request, final HttpServletResponse response)
          throws ServletException {
    return "entrar";
  }

}
