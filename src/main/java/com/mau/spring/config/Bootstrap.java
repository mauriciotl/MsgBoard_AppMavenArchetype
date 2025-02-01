package com.mau.spring.config;

import com.mau.app.filter.AuthenticationFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

@SuppressWarnings("unused")
public class Bootstrap implements WebApplicationInitializer
{
    @Override
    public void onStartup(ServletContext container) {

        // Register CharacterEncodingFilter
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        //Filters
        FilterRegistration.Dynamic filterRegistration =
                container.addFilter("characterEncodingFilter", characterEncodingFilter);
        filterRegistration.addMappingForUrlPatterns(null, false, "/*");

        // Register the AuthenticationFilter
        filterRegistration = container.addFilter("AuthenticationFilter", new AuthenticationFilter());
        filterRegistration.addMappingForUrlPatterns(null, false, "/*"); // Apply to all URLs


        container.getServletRegistration("default").addMapping("/resource/*");

        AnnotationConfigWebApplicationContext rootContext =
                new AnnotationConfigWebApplicationContext();
        rootContext.register(RootContextConfiguration.class);
        container.addListener(new ContextLoaderListener(rootContext));

        AnnotationConfigWebApplicationContext servletContext =
                new AnnotationConfigWebApplicationContext();
        servletContext.register(ServletContextConfiguration.class);
        ServletRegistration.Dynamic dispatcher = container.addServlet(
                "springDispatcher", new DispatcherServlet(servletContext)
        );
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

    }
}
