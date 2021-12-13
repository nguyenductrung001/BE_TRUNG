package com.itsol.recruit_managerment.dto;
import com.itsol.recruit_managerment.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupDTO {
    private String  fullName;
    private String  email;
    private String userName;
    private String password;
    private String  phoneNumber;
    private String  homeTown;
    private String  gender;
    private String birthDay;
    private  String active;
    private Set<Role> roles;
}
