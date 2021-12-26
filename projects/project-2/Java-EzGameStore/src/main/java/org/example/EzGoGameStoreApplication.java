package org.example;

import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
public class EzGoGameStoreApplication {
    @Autowired
    private UserService userService;

//  @PostConstruct
//    public void registerUsers(){
//        User user=new User();
//        user.setPassword("123");
//        user.setUsername("anotherTest"); ///make sure its unique if you use this
//        List<String> authorities1=List.of("customer_account");
//        user.setAuthorities(authorities1);
//        userService.register(user);


   // }
    public static void main(String[] args) {
        SpringApplication.run(EzGoGameStoreApplication.class, args);
    }


}
