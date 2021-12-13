package com.itsol.recruit_managerment.vm;

import lombok.Data;

import java.util.Date;

@Data
public class JobVM {
    //    @NotEmpty
//    @NotBlank(message = "jobName không được để trống")
//    @Size(max = 200,message = "tên không được vượt quá 150 kí tự")
    String job_name;
    //    @NotEmpty(message = " số năm kinh nghiệm không được để trống phải lớn hơn 0")
    Integer number_experience;
    //    @NotEmpty(message = " địa chỉ làm việc phải nhỏ hơn 300 ký tự")
    String address_work;
    //    @NotEmpty(message = " số lượng cần tuyển luôn phải lớn hơn hoặc bằng 1")
    Integer qty_person;
    //    @NotEmpty(message = " ngày cấp  không được để trống")
    String tax_code;
    //    @NotEmpty(message = " mã số thuế không được để trống")
    Date tax_date;
    //    @NotEmpty(message = " trụ sở chính  không được để trống")
    String head_office;
    //    @NotEmpty(message = " số lượng nhân viên là số và lớn hơn 0")
    String number_staff;
    //    @NotEmpty(message = " link web  không được để trống")
    String link_web;
    //    @NotEmpty(message = " mô tả  không được để trống và không vượt quá 2000 từ")
    String description;
    //    @NotEmpty(message = " avatar  không được để trống")
    String avatar;
    //    @NotEmpty(message = " ảnh bìa  không được để trống")
    String backdrop_img;
    //    @NotEmpty(message = "   không được để trống")
    Integer is_delete;


}
