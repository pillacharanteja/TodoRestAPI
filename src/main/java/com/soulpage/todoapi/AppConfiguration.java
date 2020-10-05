
package com.soulpage.todoapi;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Below class is a configuration class which is loaded by spring container at
 * the time of application loading Below we are enabling CORS related settings
 * by allowing all origins(it will allow request from any domain) ,all
 * headers(any header is accepted in the request) and all methods(like
 * get,put,post,options ... ) Added On : 20-09-2019
 *
 * @author Charan Teja
 */
@EnableWebMvc
@Configuration
public class AppConfiguration implements WebMvcConfigurer {

    AppConfiguration() {
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");

    }

}
