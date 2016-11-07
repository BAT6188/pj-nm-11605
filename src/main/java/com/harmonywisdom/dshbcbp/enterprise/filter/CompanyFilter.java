package com.harmonywisdom.dshbcbp.enterprise.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2016/11/7.
 */
public class CompanyFilter implements Filter{

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = ((HttpServletRequest) req);
        HttpSession session = request.getSession();
        String requestUrl = request.getRequestURI();
        if ((session != null && session.getAttribute("session") != null)
                || requestUrl.contains("/container/company/login")) {
            chain.doFilter(req, resp);
        } else {
            ((HttpServletResponse) resp).sendRedirect(((HttpServletRequest) req).getContextPath() + "/container/company/login/login.jsp");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
