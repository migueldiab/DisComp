package edu.ort.discomp.framework;

import javax.servlet.*;
import javax.servlet.http.*;

public class HomeWebAction extends WebAction
{
	/**
	 * Method 'execute'
	 * 
	 * @param servlet
	 * @param request
	 * @param response
	 * @throws ServletException
	 */
  @Override
	public String execute(CommandFactory servlet, HttpServletRequest request, HttpServletResponse response) throws ServletException
	{
		try {		
			forward( servlet, request, response, "/index.jsp" );
		}
		catch (Exception e) {
			throw new ServletException( "Failed to process request", e );
		}
    return null;
	}

}
