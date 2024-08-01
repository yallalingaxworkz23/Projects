package com.xworkz.Pinxworkz.configraction;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
@EnableScheduling
@ComponentScan("com.xworkz.Pinxworkz")
public class BeanConfiguration {
	
	public BeanConfiguration() {
	System.out.println("Bean configuration is created..");
	}
	
	@Bean
   public ViewResolver resolver() {
		System.out.println("running in the vierresolver..");
		ViewResolver resolver=new InternalResourceViewResolver("/",".jsp");
	return resolver;
	}
	
	 @Bean
	    public LocalContainerEntityManagerFactoryBean getEntity(){

	        return  new LocalContainerEntityManagerFactoryBean();
	    }


}
