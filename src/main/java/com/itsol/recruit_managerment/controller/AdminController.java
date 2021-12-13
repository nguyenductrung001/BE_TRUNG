package com.itsol.recruit_managerment.controller;
import com.itsol.recruit_managerment.config.AccountActivationConfig;
import com.itsol.recruit_managerment.constant.DateTimeConstant;
import com.itsol.recruit_managerment.dto.UserSignupDTO;
import com.itsol.recruit_managerment.email.EmailServiceImpl;
import com.itsol.recruit_managerment.model.OTP;
import com.itsol.recruit_managerment.model.Role;
import com.itsol.recruit_managerment.model.User;
import com.itsol.recruit_managerment.repositories.RoleRepo;
import com.itsol.recruit_managerment.service.AdminService;
import com.itsol.recruit_managerment.service.UserService;
import com.itsol.recruit_managerment.service.impl.UserServiceimpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
@Autowired
    RoleRepo roleRepo;
@Autowired
    UserServiceimpl userServiceimpl;
@Autowired
    UserService userService;
@Autowired
    AccountActivationConfig accountActivationConfig;
@Autowired
    EmailServiceImpl emailService;
@Autowired
    AdminService adminService;
@PostMapping("/singupje")
public ResponseEntity<String>  singupje(@RequestBody UserSignupDTO userSignupDTO){
    Role role = roleRepo.findByName("ROLE_JE");
    User user = userServiceimpl.createUser(userSignupDTO);
    if(ObjectUtils.isEmpty(user)){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
    Set<Role> roles = new HashSet<>();
    roles.add(role);
    user.setRoles(roles);
    SimpleDateFormat sdf = new SimpleDateFormat(DateTimeConstant.YYYYMMDD_FOMART);
    try {
        user.setBirthDay(sdf.parse(userSignupDTO.getBirthDay()));
    } catch (java.text.ParseException e) {
        e.printStackTrace();
    }
    user.setActive(false);
    user.setIsDelete(0);
    userService.saveUser(user);
    OTP otp = userService.generateOTP(user);
    String linkActive = accountActivationConfig.getActivateUrl() + user.getId();
    emailService.sendSimpleMessage(user.getEmail(),"Link active account","<a href=\" " + linkActive + "\">Click vào đây để kích hoạt tài khoản</a>");
    return ResponseEntity.ok().body("check email for OTP");
}
    @GetMapping("/active/{id}")
    public ResponseEntity<String> activeAccount(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            if (user.isActive()) {
                return ResponseEntity.ok().body("Your account is already activated");
            }
            userService.activeAccount(user);
            return ResponseEntity.ok().body("Active successfull");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/getallje")
    public Object getAllJe(){
        return  userService.getAllJE();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id , @RequestBody UserSignupDTO userSignupDTO) {
        try {
            adminService.update(userSignupDTO,id);
            return  ResponseEntity.ok().body(userSignupDTO);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("failed to update user");
        }
}
    @PutMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id ) {
        try {
            adminService.delete(id);
            return  ResponseEntity.ok().body("xóa thành công");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("failed to update user");
        }
    }
}
