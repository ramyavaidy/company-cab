package com.company.cab;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EntityScan(basePackages= {"com.company.cab.*"})
@ComponentScan({"com.company.cab.*"})
@ComponentScan(basePackageClasses = {SwaggerConfig.class})
@SpringBootApplication
public class CompanyCabApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyCabApplication.class, args);
	}
}
