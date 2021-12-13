package com.itsol.recruit_managerment.controller;


import com.itsol.recruit_managerment.constant.Constants;
import com.itsol.recruit_managerment.dto.PasswordDTO;
import com.itsol.recruit_managerment.dto.UserSignupDTO;
import com.itsol.recruit_managerment.email.EmailServiceImpl;
import com.itsol.recruit_managerment.model.OTP;
import com.itsol.recruit_managerment.model.User;
import com.itsol.recruit_managerment.security.AuthenFilter;
import com.itsol.recruit_managerment.service.impl.ProfilesServiceimpl;
import com.itsol.recruit_managerment.service.impl.UserServiceimpl;
import com.itsol.recruit_managerment.vm.FogotPasswordVM;
import com.itsol.recruit_managerment.vm.UserVM;
import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.naming.AuthenticationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserServiceimpl userService;
    @Autowired
    private  EmailServiceImpl emailService;
    @Autowired
    private ProfilesServiceimpl  profilesServiceimpl;
    private AuthenticationManager authenticationManager;
    private AuthenFilter authenFilter;
    @Autowired
    private UserDetailsService userDetailsService;

    private Response response;
    @GetMapping("/all")
    public List<User> getAll(){
        return userService.getData();
    }

    @PostMapping()
    public ResponseEntity<Object> add(@Valid @RequestBody UserVM userVM, BindingResult result) {
        if(result.hasErrors()){
            return  ResponseEntity.badRequest().body(result.getAllErrors());
        }
        try {
            userService.add(userVM);

            return  ResponseEntity.ok().body(userVM);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("failed to create user");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id , @RequestBody UserSignupDTO userSignupDTO) {
        try {
            userService.update(userSignupDTO,id);
            return  ResponseEntity.ok().body(userSignupDTO);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("failed to update user");
        }

    }
    @DeleteMapping("/{id}")
    public int delete(@PathVariable Long id ) {
        return userService.deleteById(id);
    }
    @GetMapping("/search/{fullName}")
    public  List<User> search(@PathVariable String fullName){
        return userService.searchName(fullName);
    }


    @GetMapping("/users/info/get-otp")
    public ResponseEntity<String> getOTP(){
        try {
            User user = userService.loadUserFromContext();
            OTP otp = userService.retrieveNewOTP(user);
            emailService.sendSimpleMessage(user.getUserName(), "OTP", "OTP: " + otp.getCode());
            return ResponseEntity.ok().body("check email for OTP");
        }catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/users/info/change-password")
    public ResponseEntity<String> changePasswordRequest(@RequestBody PasswordDTO passwordDTO){
        User user = userService.loadUserFromContext();
        try {
            if(userService.verifyPassword(user, passwordDTO)){
                OTP otp = userService.retrieveNewOTP(user);
                emailService.sendSimpleMessage(user.getUserName(),
                        "Change password",
                        "OTP: " + otp.getCode() + "\nNew password: " + passwordDTO.getNewPassword());
                return ResponseEntity.ok().body("Check mail for OTP to commit changing");
            }else{
                return ResponseEntity.badRequest().body("Failed changing password");
            }
        }catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/users/info/change-password")
    public ResponseEntity<String> changePassword(@RequestParam String otpCode, @RequestParam String password){
        try {
            User user = userService.loadUserFromContext();
            OTP otp = userService.getOTPByUser(user);
            userService.verifyOTP(otp, otpCode);
            userService.changePassword(password, user);
            return ResponseEntity.ok().body("Password change successfull");
            }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/fogotpass")
    public ResponseEntity<String> fogotPassword(@RequestBody FogotPasswordVM fogotPasswordVM){
        return (ResponseEntity<String>) userService.sendFogotPasswordMail(fogotPasswordVM.getEmail());
    }

//    @PostMapping("/authenticate")
//    public @ResponseBody
//    ResponseEntity<String>  createAuthenticationTokenAndRole(@RequestBody UserSignupDTO form) {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(form.getUserName(), form.getPassword())
//            );
//        } catch (RuntimeException e) {
//            return ResponseEntity.internalServerError().body(e.getMessage());
//        }
//        final UserDetails userDetails = userDetailsService
//                .loadUserByUsername(form.getUserName());
//        final String jwt = authenFilter.generateToken(userDetails);
//        final List<String> roles = new ArrayList<>();
//        userDetails.getAuthorities().stream().forEach(grantedAuthority -> {
//            roles.add(grantedAuthority.getAuthority());
//        });
//        Map<String, Object> data = new HashMap<>();
//        data.put("jwt", jwt);
//        data.put("roles", roles);
//         return ResponseEntity.ok().body("Login thanh cong");
//    }

}
