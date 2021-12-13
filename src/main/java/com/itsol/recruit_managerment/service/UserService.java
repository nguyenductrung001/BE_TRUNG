package com.itsol.recruit_managerment.service;

import com.itsol.recruit_managerment.dto.PasswordDTO;
import com.itsol.recruit_managerment.dto.UserSignupDTO;
import com.itsol.recruit_managerment.model.OTP;
import com.itsol.recruit_managerment.model.Role;
import com.itsol.recruit_managerment.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    User saveUser(User user);

    Role saveRole(Role role);

    OTP saveOTP(OTP otp);

    void addRoleToUser(String username, String roleName);

    User getUser(String username);

    User getUserById(Long id);

    List<User> getAllUsers();

    OTP generateOTP(User user);

    OTP getOTPByUser(User user);

    void verifyOTP(OTP otp, String otpCode);

    OTP retrieveNewOTP(User user);

    void activeAccount(User user);

    boolean verifyPassword(User user, PasswordDTO passwordDTO);

    void changePassword(String password, User user);

    User loadUserFromContext();

    Object sendFogotPasswordMail(String email);

    User createUser(UserSignupDTO userSignupDTO);

    Object getAllJE();
}
