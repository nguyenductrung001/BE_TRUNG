package com.itsol.recruit_managerment.service;

import com.itsol.recruit_managerment.model.JobRegister;
import com.itsol.recruit_managerment.model.User;
import org.springframework.data.domain.Page;

public interface JobRegisterService {
    Page<JobRegister> getJobRegister(Integer page, Integer size);
    JobRegister saveJobRegister(JobRegister jobRegister);
}
