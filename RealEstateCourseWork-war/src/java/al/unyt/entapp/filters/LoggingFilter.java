/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.unyt.entapp.filters;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 *
 * @author User
 */
public class LoggingFilter implements Filter {
    
    FilterConfig filterConf;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConf = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            
             String clientIP = ((HttpServletRequest)request).getRemoteHost();
             String applicationURL=((HttpServletRequest)request).getContextPath() + getRelativeUrl(((HttpServletRequest)request));
             HttpSession session = ((HttpServletRequest)request).getSession();
             Date timeOfAccess = new Date(session.getCreationTime());
             DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);
             String contextPath = ((HttpServletRequest)request).getContextPath();
             
            filterConf.getServletContext().log("CLIENT IP :[ "+clientIP+" ]\nAPPLICATION URL : [ "
             +applicationURL+" ]\nTIME OF ACCESS : ["+formatter.format(timeOfAccess)); 
            
             
             chain.doFilter(request, response);
    }

    public static String getRelativeUrl(
    HttpServletRequest request ) {

    String baseUrl = null;

    if ( ( request.getServerPort() == 80 ) ||
         ( request.getServerPort() == 443 ) )
      baseUrl =
        request.getScheme() + "://" +
        request.getServerName() +
        request.getContextPath();
    else
      baseUrl =
        request.getScheme() + "://" +
        request.getServerName() + ":" + request.getServerPort() +
        request.getContextPath();

    StringBuffer buf = request.getRequestURL();

    if ( request.getQueryString() != null ) {
      buf.append( "?" );
      buf.append( request.getQueryString() );
    }

    return buf.substring( baseUrl.length() );
  }

    @Override
    public void destroy() {
    }
    
}
