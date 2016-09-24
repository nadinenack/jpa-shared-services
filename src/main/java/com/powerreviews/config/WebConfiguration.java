package com.powerreviews.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
/**
 * Created by nadinenack on 9/23/16.
 */
@org.springframework.context.annotation.Configuration
public class WebConfiguration {
    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }
}
