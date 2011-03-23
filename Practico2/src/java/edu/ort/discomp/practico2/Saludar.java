package edu.ort.discomp.practico2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumno
 */
public class Saludar extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String parameter = req.getParameter("name");
    resp.setContentType("text/html");
    PrintWriter writer = resp.getWriter();
    writer.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"main.css\" media=\"screen\" /></link>");
    writer.println("<h1>");
    writer.println("Hola ");
    writer.println(parameter);

    writer.println("Soy un proceso de long polling...<br/>");
    for (int i= 0; i < 100; i++) {
      writer.println("Me llamaron " + i + " veces<br/>");
      writer.flush();
      for (int j= 0; j < 10000; j++) {
        System.out.println("Test///");
      }
    }
    writer.println("</h1><br/>");
    
    
  }
  
}
