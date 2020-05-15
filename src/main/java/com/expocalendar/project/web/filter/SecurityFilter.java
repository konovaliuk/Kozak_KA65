package com.expocalendar.project.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SecurityFilter implements Filter {
    private String loginPath;

    @Override
    public void init(FilterConfig fConfig) {
        loginPath = fConfig.getInitParameter("LOGIN_PATH");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;


        if (httpRequest.getSession().getAttribute("account") == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + loginPath);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
