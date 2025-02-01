package com.mau.app.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic, if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false); // Do not create a new session if it doesn't exist

        // Check if the user is logged in
        boolean isLoggedIn = (session != null && session.getAttribute("loggedInUser") != null);

        // Get the request URI
        String requestURI = httpRequest.getRequestURI();

        // Allow access to the login page and static resources (CSS, JS, etc.)
        boolean isLoginPage = requestURI.endsWith("/login");
        boolean isStaticResource = requestURI.startsWith("/resources/"); // Adjust this based on your static resource path

        if (isLoggedIn || isLoginPage || isStaticResource) {
            // User is logged in or accessing the login page, so continue the request
            chain.doFilter(request, response);
        } else {
            // User is not logged in, redirect to the login page
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        }
    }

    @Override
    public void destroy() {
        // Cleanup logic, if needed
    }
}