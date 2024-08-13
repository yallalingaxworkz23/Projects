package com.xworkz.Pinxworkz.configraction;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import lombok.extern.slf4j.Slf4j;

@EnableWebMvc
@Configuration
@EnableScheduling
@ComponentScan("com.xworkz.Pinxworkz")
@Slf4j
public class BeanConfiguration {
	
	public BeanConfiguration() {
	log.info("Bean configuration is created..");
	}
	
	@Bean
   public ViewResolver resolver() {
		log.info("running in the vierresolver..");
		ViewResolver resolver=new InternalResourceViewResolver("/",".jsp");
	return resolver;
	}
	
	
	@Bean
    public MultipartResolver multipartResolver() {
		log.info("creating MultipartResolver...........");
        return new StandardServletMultipartResolver();
        
    }
	
	
	 @Bean
	    public LocalContainerEntityManagerFactoryBean getEntity(){

	        return  new LocalContainerEntityManagerFactoryBean();
	    }


}
