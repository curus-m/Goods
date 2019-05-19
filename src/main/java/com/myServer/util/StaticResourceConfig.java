package com.myServer.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class StaticResourceConfig implements WebMvcConfigurer{

    @Value("${static.resource.location.dakimakura}")
    private String dakimakuraResourceLocation;
    
    @Value("${static.resource.location.eroge}")
    private String erogeResourceLocation;
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/dakimakura/**")
        .addResourceLocations(dakimakuraResourceLocation).setCachePeriod(3600);
        registry.addResourceHandler("/resources/eroge/**")
        .addResourceLocations(erogeResourceLocation).setCachePeriod(3600);
    }
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}