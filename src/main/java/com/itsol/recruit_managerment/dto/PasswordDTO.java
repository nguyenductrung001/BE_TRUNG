package com.itsol.recruit_managerment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordDTO {

    private String currentPassword;
    private String newPassword;
    private String verifyNewPassword;

    public boolean checkMatch(String password){
        if(newPassword == null ||
                verifyNewPassword == null ||
                currentPassword == null ||
                !newPassword.equals(verifyNewPassword) ||
                !currentPassword.equals(password)){
            return false;
        }
        return true;
    }

}
