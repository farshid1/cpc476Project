package com.codeiners.auth;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by razorhead on 10/26/14.
 */
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)req).getSession(false);
        if(session == null || session.getAttribute("username") == null)
        {
            ((HttpServletResponse)res).sendRedirect(
                    ((HttpServletRequest) req).getContextPath() + "/"
            );
        }
        else
            chain.doFilter(req, res);
    }

    @Override
    public void destroy() {}
}
