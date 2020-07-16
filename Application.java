package com.yabonza.dog.breed.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;


@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackages="com.yabonza.dog.breed")
public class Application  extends SpringBootServletInitializer implements WebApplicationInitializer{
	public static void main(String args[]){
		SpringApplication.run(HireBuzzz.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(HireBuzzz.class);
	} 
	
}
