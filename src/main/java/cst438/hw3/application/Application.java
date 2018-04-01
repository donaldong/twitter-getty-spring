package cst438.hw3.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"cst438.hw3.application", "cst438.hw3.twitter", "cst438.hw3.getty"} )
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
