package com.itsol.recruit_managerment.controller;

import com.itsol.recruit_managerment.model.Profiles;
import com.itsol.recruit_managerment.model.User;
import com.itsol.recruit_managerment.service.impl.ProfilesServiceimpl;
import com.itsol.recruit_managerment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Slf4j
@RestController
@RequestMapping("/api/user/profile")
public class ProfileController {
    @Autowired
    ProfilesServiceimpl profilesServiceimpl;
    @Autowired
    UserService userServiceimpl;

    @GetMapping("/{id}")
    public Profiles getById(@PathVariable Long id) throws ParseException {
        Profiles profiles = profilesServiceimpl.getById(id);


        String date = profiles.getUser().getBirthDay().toString();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        User user = profiles.getUser();
        User newUser = new User();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            newUser.setBirthDay(sdf.parse(user.getBirthDay().toString()));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        newUser.setId(id);
        newUser.setGender(user.getGender());
        newUser.setEmail(user.getEmail());
        newUser.setHomeTown(user.getHomeTown());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setFullName(user.getFullName());
        newUser.setUserName(user.getUserName());
        newUser.setPassword(user.getPassword());
        profiles.setUser(newUser);
        return profiles;
    }

    @PostMapping()
    public ResponseEntity<Object> add(@Valid @RequestBody Profiles profiles) {
        try {
//            User user=userServiceimpl.getUserById(id);
//            profiles.setUser(user);
            profilesServiceimpl.save(profiles);
            return ResponseEntity.ok().body(profiles);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("fail");
        }
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Object> deleteProfiles(@PathVariable Long id) {
        profilesServiceimpl.deleteById(id);
        return ResponseEntity.badRequest().body("abc");
    }


}
