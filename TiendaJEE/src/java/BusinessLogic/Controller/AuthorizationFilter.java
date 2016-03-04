/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthorizationFilter implements Filter {
 
    public AuthorizationFilter() {
    }
 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
 
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        try {
        
            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);
 
            String reqURI = reqt.getRequestURI();
            int rol = 0;
            
            if (ses != null && ses.getAttribute("rol") != null) {
                String rolId = ses.getAttribute("rol").toString();
                rol = Integer.parseInt(rolId);
                System.out.println("El Rol "+Integer.toString(rol));
            }
            
            if (reqURI.contains("product")) {
                if (rol == 2 || rol == 1) {
                    chain.doFilter(request, response);
                }
                else {
                    resp.sendRedirect(reqt.getContextPath() + "/faces/unauthorized.xhtml");
                }
            }
            
            else if (reqURI.contains("user")) {
                if (rol == 1) {
                    chain.doFilter(request, response);
                }
                else {
                    resp.sendRedirect(reqt.getContextPath() + "/faces/unauthorized.xhtml");
                }
            }
            else {
                chain.doFilter(request, response);
            }
            
            /*if (reqURI.contains("/login.xhtml")
                    || (ses != null && ses.getAttribute("username") != null)
                    || reqURI.contains("/public/")
                    || reqURI.contains("javax.faces.resource"))
                chain.doFilter(request, response);
            else
                resp.sendRedirect(reqt.getContextPath() + "/faces/login.xhtml");*/
        } catch (IOException | ServletException e) {
            System.out.println(e.getMessage());
        }
    }
 
    @Override
    public void destroy() {
 
    }
}
