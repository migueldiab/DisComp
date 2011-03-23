package edu.ort.discomp.framework;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Administrator
 */
public class WebAdmin {

  public static Cookie registrarCookie(String nombreCookie, String valorCookie) {
//    Date now = new Date();
//    String timestamp = now.toString();
    Cookie cookie = new Cookie (nombreCookie,valorCookie);
    cookie.setMaxAge(1 * 8 * 60 * 60);
    return cookie;
  }
  public static Cookie expirarCookie(String nombreCookie) {
//    Date now = new Date();
//    String timestamp = now.toString();
    Cookie cookie = new Cookie (nombreCookie,"");
    cookie.setMaxAge(0);
    return cookie;
  }

  public static boolean usuarioAutenticado(HttpServletRequest request) {
    if (obtenerCookie(request, "usuario")!=null) {
      return true;
    }
    return false;
  }

  public static String obtenerUsuario(HttpServletRequest request) {
    return obtenerCookie(request, "usuario").getValue();
  }

  private static Cookie obtenerCookie(HttpServletRequest request, String cookieName) {
    Cookie cookies [] = request.getCookies ();
    Cookie cookieResponse = null;
    if (cookies != null)
    {
      for (int i = 0; i < cookies.length; i++)
      {
        if (cookies[i].getName().equals(cookieName))
        {
          cookieResponse = cookies[i];
          return cookieResponse;
        }
      }
    }
    return null;
  }

}
