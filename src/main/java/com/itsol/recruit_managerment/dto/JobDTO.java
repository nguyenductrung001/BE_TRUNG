package com.itsol.recruit_managerment.dto;

import java.util.Date;

public class JobDTO {
    Long id;

    String job_name;


    Integer job_position_id;
    Integer number_experience;
    //    @Column(name = "method_work_id")
    Integer method_work_id;
//    private Method_work method_work;
    String address_work;
    Integer academic_level_id;
    Integer level_id;
    Integer qty_person;
    Date due_date;
    String skills;
    String description;
    String interrest;
    Integer salary;
    Integer contact_id;
    Integer job_status_id;
    Integer views;
    Integer create_id;
    Integer is_delete;
}
