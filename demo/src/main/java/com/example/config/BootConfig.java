package com.example.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages={"com"})
@EnableAutoConfiguration(exclude={HibernateJpaAutoConfiguration.class})
public class BootConfig extends SpringBootServletInitializer{

	@Override
	public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(BootConfig.class);
	}
 
	public static void main(String[] args) {
		SpringApplication.run(BootConfig.class, args);
	}
}
