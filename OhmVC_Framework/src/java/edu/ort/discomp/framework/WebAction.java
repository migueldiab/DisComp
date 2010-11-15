package edu.ort.discomp.framework;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.math.BigDecimal;

public abstract class WebAction
{
    private static final SimpleDateFormat df = new SimpleDateFormat( "dd-MMM-yyyy" );

	/**
	 * Method 'execute'
	 *
	 * @param servlet
	 * @param request
	 * @param response
	 * @throws ServletException
	 */
	public abstract String execute(FrontController servlet, HttpServletRequest request, HttpServletResponse response) throws ServletException;

	/**
	 * Method 'forward'
	 *
	 * @param servlet
	 * @param request
	 * @param response
	 * @param page
	 * @throws ServletException
	 */
	public void forward(FrontController servlet, HttpServletRequest request, HttpServletResponse response, String page) throws ServletException
	{
		try {
			ServletContext ctx = servlet.getServletContext();
			RequestDispatcher dispatcher = ctx.getRequestDispatcher(page);
			if (dispatcher == null) {
				throw new RuntimeException( "No dispatcher found for " + page );
			}
			dispatcher.forward(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServletException( "Failed to process request", e );
		}

	}

    public String parseString(HttpServletRequest request, String paramName) {
        return request.getParameter( paramName );
    }

    public BigDecimal parseBigDecimal(HttpServletRequest request, String paramName) {
        return new BigDecimal( parseString(request, paramName) );
    }

    public Date parseDate(HttpServletRequest request, String paramName)
        throws ParseException {

        synchronized (df) {
            return df.parse(request.getParameter( paramName ));
        }
    }

    public short parseShort(HttpServletRequest request, String paramName) {
        return Short.parseShort( parseString(request, paramName) );
    }

    public int parseInt(HttpServletRequest request, String paramName) {
        return Integer.parseInt( parseString(request, paramName) );
    }

    public long parseLong(HttpServletRequest request, String paramName) {
        return Long.parseLong( parseString(request, paramName) );
    }

    public float parseFloat(HttpServletRequest request, String paramName) {
        return Float.parseFloat( parseString(request, paramName) );
    }

    public double parseDouble(HttpServletRequest request, String paramName) {
        return Double.parseDouble( parseString(request, paramName) );
    }
}


