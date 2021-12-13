package com.itsol.recruit_managerment.config;

import com.itsol.recruit_managerment.model.User;
import com.itsol.recruit_managerment.model.UserRole;
import com.itsol.recruit_managerment.repositories.IUserRespository;
import com.itsol.recruit_managerment.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    IUserRespository iUserRespository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = iUserRespository.findByUserName("admin");
        if (user != null) {
            return;
        }
        user = new User();
        user.setUserName("admin");
        user.setFullName("admin");
        user.setEmail("trungndph10309@fpt.edu.vn");
        user.setActive(true);
        user.setPassword(passwordEncoder.encode("admin"));
        user = iUserRespository.save(user);
        UserRole role = new UserRole();
        UserRole.MyIdclass myIdclass = new UserRole.MyIdclass();
        myIdclass.setRolesId(2);
        myIdclass.setUsersId(user.getId());
        role.setId(myIdclass);
        userRoleRepository.save(role);
    }
}
