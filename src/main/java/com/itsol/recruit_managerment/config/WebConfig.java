package com.itsol.recruit_managerment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    CommandLineRunner run(AppUserService userService){
//        return args -> {
//            userService.saveRole(new Role(null, "ROLE_USER"));
//            userService.saveRole(new Role(null, "ROLE_ADMIN"));
//            userService.saveRole(new Role(null, "ROLE_MANAGER"));
//
//            userService.saveUser(new AppUser(null, "quan", "123", new HashSet<>(), true));
//            userService.saveUser(new AppUser(null, "hoang", "1234", new HashSet<>(), true));
//            userService.saveUser(new AppUser(null, "trung", "1235", new HashSet<>(), true));
//            userService.saveUser(new AppUser(null, "kiet", "1236", new HashSet<>(), false));
//
//
//            userService.addRoleToUser("quan", "ROLE_ADMIN");
//            userService.addRoleToUser("quan", "ROLE_USER");
//            userService.addRoleToUser("hoang", "ROLE_MANAGER");
//            userService.addRoleToUser("trung", "ROLE_USER");
//            userService.addRoleToUser("kiet", "ROLE_USER");
//        };
//    }
}
