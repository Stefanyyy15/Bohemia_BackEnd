package com.redsocial.bohemia;

import com.redsocial.bohemia.domain.service.CommentService;
import com.redsocial.bohemia.domain.service.TokenService;
import com.redsocial.bohemia.persistence.entity.Token;
import java.time.LocalDateTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BohemiaApplication {

	public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BohemiaApplication.class, args);
                CommentService commentService = context.getBean(CommentService.class);

	}

        //        1. Agregar admin
//       Comment com = new Comment();
//       com.setid_user(1);
//       com.setcommentDate("");
//       com.setcomment("Ay, pero por Dios que hermosa");
//       commentService.saveComment(com);

}
