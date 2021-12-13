package com.itsol.recruit_managerment.vm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyVM {
    @NotEmpty
    @NotBlank(message = "fullName không được để trống")
    @Size(max = 200,message = "tên không được vượt quá 200 kí tự")
    String name;
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email phải đúng định dạng")
    String email;
    @NotEmpty(message = " số điện thoại không được để trống phải nhỏ hơn 12")
    @PositiveOrZero(message = "Số điện thoại phải là số nguyên dương")
    String hot_line;
    @NotEmpty(message = " ngày thành lập  không được để trống")
    Date date_incoporation;
    @NotEmpty(message = " ngày cấp  không được để trống")
    String tax_code;
    @NotEmpty(message = " mã số thuế không được để trống")
    Date tax_date;
    @NotEmpty(message = " trụ sở chính  không được để trống")
    String head_office;
    @NotEmpty(message = " số lượng nhân viên là số và lớn hơn 0")
    String number_staff;
    @NotEmpty(message = " link web  không được để trống")
    String link_web;
    @NotEmpty(message = " mô tả  không được để trống và không vượt quá 2000 từ")
    String description;
    @NotEmpty(message = " avatar  không được để trống")
    String avatar;
    @NotEmpty(message = " ảnh bìa  không được để trống")
    String backdrop_img;
    @NotEmpty(message = "   không được để trống")
    Boolean is_delete;

}
