package by.javaguru.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

import static by.javaguru.util.UrlPath.LOGIN;
import static by.javaguru.util.UrlPath.REGISTRATION;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {
    private static final Set<String> PUBLIC_PATH = Set.of(LOGIN, REGISTRATION);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();

        if (isPublicPath(requestURI) || isUserLoggedIn(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse)servletResponse).sendRedirect(LOGIN);
        }
    }

    private boolean isUserLoggedIn(ServletRequest servletRequest) {
        var user = ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user != null;
    }

    private boolean isPublicPath(String requestURI) {
        return PUBLIC_PATH.stream().anyMatch(requestURI::startsWith);
    }
}
