package com.expocalendar.project.web.filter;

import com.expocalendar.project.entities.Account;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter implements Filter {
    private String mainPath;

    @Override
    public void init(FilterConfig fConfig) {
        mainPath = fConfig.getInitParameter("MAIN_PATH");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        Account account = (Account) httpRequest.getSession().getAttribute("account");

        if (account != null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + mainPath);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
