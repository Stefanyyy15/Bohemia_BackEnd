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

//                postService.delPost(10L);
//                postService.delPost(11L);
//                postService.delPost(12L);

        
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
//    }
    //        1. Agregar admin
//       Comment com = new Comment();
//       com.setid_user(1);
//       com.setcommentDate("");
//       com.setcomment("Ay, pero por Dios que hermosa");
//       commentService.saveComment(com);
    }
}
