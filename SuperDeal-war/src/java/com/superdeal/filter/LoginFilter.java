package com.superdeal.filter;

import com.superdeal.contoller.LoginController;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *handle session 
 *@author udith dissanayake
 * @version 1.0 (20/8/2015)
 */
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        LoginController session = (LoginController) req.getSession().getAttribute("loginController");
        String url = req.getRequestURI();
        /*
         * if request is for logout there is no session, redirect the request to login.xhtml
         * if request is for login there is a session, redirect the request to index.xhtml
         * 
         */
        if (session == null || !session.isLogged) {
            if (url.indexOf("forum.xhtml") >= 0 || url.indexOf("logout.xhtml") >= 0) {
                resp.sendRedirect(req.getServletContext().getContextPath() + "/login.xhtml");
            } else {
                chain.doFilter(request, response);
            }
        } else {
            if (url.indexOf("forum.xhtml") >= 0 || url.indexOf("login.xhtml") >= 0) {
                resp.sendRedirect(req.getServletContext().getContextPath() + "/forum.xhtml");
            }else if (url.indexOf("logout.xhtml") >= 0) {
                req.getSession().removeAttribute("loginController");
                resp.sendRedirect(req.getServletContext().getContextPath() + "/login.xhtml");
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
