package com.mau.spring.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mau.app.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.web.servlet.RequestToViewNameTranslator;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.DefaultRequestToViewNameTranslator;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.mau")
public class ServletContextConfiguration implements WebMvcConfigurer {

    private final ObjectMapper objectMapper;
    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    @Autowired
    public ServletContextConfiguration(ObjectMapper objectMapper, Marshaller marshaller, Unmarshaller unmarshaller) {
        this.objectMapper = objectMapper;
        this.marshaller = marshaller;
        this.unmarshaller = unmarshaller;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // This method maps URL paths to locations on the file system where static resources are stored.
        // In this case, it serves all resources under /css/ from the application's "css" directory.
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        //This tells Spring to serve files under the /js/ URL path from the src/main/webapp/js/ directory.
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");

    }



    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter(objectMapper));

        MarshallingHttpMessageConverter xmlConverter = new MarshallingHttpMessageConverter();
        xmlConverter.setSupportedMediaTypes(List.of(MediaType.APPLICATION_XML, MediaType.TEXT_XML));
        xmlConverter.setMarshaller(this.marshaller);
        xmlConverter.setUnmarshaller(this.unmarshaller);
        converters.add(xmlConverter);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                .ignoreAcceptHeader(false) // Enable Accept header-based negotiation
                .defaultContentType(MediaType.APPLICATION_JSON) // Default to JSON if no Accept header is provided
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("json", MediaType.APPLICATION_JSON);
    }


    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/jsp/view/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public RequestToViewNameTranslator viewNameTranslator() {
        return new DefaultRequestToViewNameTranslator();
    }


}
