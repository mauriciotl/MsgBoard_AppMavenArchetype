package com.mau.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


// Annotation to define the URL pattern
@WebServlet("/helloServlet")
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");

        // Write a simple response
        response.getWriter().println("<html>");
        response.getWriter().println("<head><title>HelloServlet</title></head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<h1>Hello, welcome to the HelloServlet!</h1>");
        response.getWriter().println("<h2>Working using annotations instead of web.xml file.</h2>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
}
