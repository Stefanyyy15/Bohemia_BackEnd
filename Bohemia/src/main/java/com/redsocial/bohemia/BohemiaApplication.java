package com.redsocial.bohemia;

import com.redsocial.bohemia.domain.service.PostServiceImpl;
import java.text.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BohemiaApplication {

    public static void main(String[] args) throws ParseException {
        ConfigurableApplicationContext context = SpringApplication.run(BohemiaApplication.class, args);
        PostServiceImpl postService = context.getBean(PostServiceImpl.class);

    }
}
