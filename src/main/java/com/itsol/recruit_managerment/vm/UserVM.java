package com.itsol.recruit_managerment.vm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVM {
    @NotEmpty
    @NotBlank(message = "fullName không được để trống")
    @Size(max = 20,message = "tên không được vượt quá 20 kí tự")
    String fullName;
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email phải đúng định dạng")
    String email;
    String userName;
    String password;
    @NotEmpty(message = " số điện thoại không được để trống")
    @PositiveOrZero(message = "Số điện thoại phải là số nguyên dương")
    String phoneNumber;
    @NotEmpty(message = " địa chỉ không được để trống")
    String homeTown;
    @NotEmpty(message = " giới tính  không được để trống")
    String gender;
    @NotEmpty(message = " ngày sinh không được để trống")
    String birthDay;
    @NotEmpty(message = " trạng thái không được để trống")
    String userStatus;
}