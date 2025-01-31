package com.redsocial.bohemia;

import com.redsocial.bohemia.domain.service.CommentService;
import com.redsocial.bohemia.domain.service.TokenService;
import com.redsocial.bohemia.domain.service.UserServiceImpl;
import com.redsocial.bohemia.persistence.entity.Token;
import com.redsocial.bohemia.persistence.entity.User;
import java.text.ParseException;
import java.time.LocalDateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BohemiaApplication {

    public static void main(String[] args) throws ParseException {
        ConfigurableApplicationContext context = SpringApplication.run(BohemiaApplication.class, args);
        CommentService commentService = context.getBean(CommentService.class);
        UserServiceImpl userService = context.getBean(UserServiceImpl.class);

//     User newUser = new User();
//     newUser.setFullname("Kevin Stev Romero Santacruz");
//     newUser.setUsername("Kevin_123");
//    newUser.setMail("PazEnElAriporo@email.com");
//     newUser.setPassword("hola415");
//     newUser.setProfilePhoto("photo.jpg");
//     newUser.setBiography("No se que voy a poner aqui");
//////     newUser.setPost(1L);
//
//    userService.saveUser(newUser);
    }
    //        1. Agregar admin
//       Comment com = new Comment();
//       com.setid_user(1);
//       com.setcommentDate("");
//       com.setcomment("Ay, pero por Dios que hermosa");
//       commentService.saveComment(com);
}
