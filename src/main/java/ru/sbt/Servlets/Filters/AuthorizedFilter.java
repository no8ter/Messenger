package ru.sbt.Servlets.Filters;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizedFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig){}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest rq = (HttpServletRequest) request;
        HttpServletResponse rs = (HttpServletResponse) response;
        String ServPath = rq.getServletPath();

        switch (ServPath){
            case ("/login"):
            case ("/register"):
                break;
            default:
                if (rq.getSession().getAttribute("isAuthorized") != "true") {
                    rs.sendRedirect("/login");
                    return;
                }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
