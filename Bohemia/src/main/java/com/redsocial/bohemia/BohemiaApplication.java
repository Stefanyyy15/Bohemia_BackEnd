package com.redsocial.bohemia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BohemiaApplication {

	public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BohemiaApplication.class, args);
	}

}
