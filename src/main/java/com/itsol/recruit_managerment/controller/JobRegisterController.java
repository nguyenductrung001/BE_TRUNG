package com.itsol.recruit_managerment.controller;
import com.itsol.recruit_managerment.model.JobRegister;
import com.itsol.recruit_managerment.service.impl.JobRegisterimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/jobregister")
public class JobRegisterController {
    @Autowired
    JobRegisterimpl jobRegisterimpl;

    @CrossOrigin
    @GetMapping("/{page}/{size}")
    public Page<JobRegister> getJobRegister(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return jobRegisterimpl.getJobRegister(page, size);
    }


}
