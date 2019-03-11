/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package al.unyt.entapp.filters;

import al.unyt.entapp.managedbeans.LoginBean;
import al.unyt.entapp.model.User;
import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminAuthorizationFilter implements Filter {

    FilterConfig filterConf;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        filterConf = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(true);

        String contextPath = req.getContextPath();
        String userRole = (String) session.getAttribute("userRole");

        if (session != null && session.getAttribute("userRole") != null) {

            if (session.getAttribute("userRole").equals("seller")) {
                res.sendRedirect(contextPath + "/access-denied.xhtml");
            } else if (session.getAttribute("userRole").equals("buyer")) {
                res.sendRedirect(contextPath + "/access-denied.xhtml");
            } else if (session.getAttribute("userRole").equals("admin")) {
                chain.doFilter(req, res);
            }
        } else {
            res.sendRedirect(contextPath + "/access-denied.xhtml");
        }

    }

    @Override
    public void destroy() {

    }

}
