package com.FMS.fmsbackend;



import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@EnableScheduling
@SpringBootApplication
@EntityScan(basePackages = "com.FMS.fmsbackend")
@ComponentScan(basePackages = "com.FMS.fmsbackend")
@EnableJpaRepositories(basePackages = "com.FMS.fmsbackend")  // Add this if necessary
public class FmsbackendApplication extends SpringBootServletInitializer {
    private static final Logger logger = LoggerFactory.getLogger(FmsbackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FmsbackendApplication.class, args);
	   
	}
	
}
