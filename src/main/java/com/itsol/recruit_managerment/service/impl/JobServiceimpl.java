package com.itsol.recruit_managerment.service.impl;

import com.itsol.recruit_managerment.model.Job;

import com.itsol.recruit_managerment.repositories.JobRepo;
import com.itsol.recruit_managerment.service.JobService;



import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class JobServiceimpl implements JobService {

    JobRepo jobRepo;


    @Override
    public List<Job> getAllJob() {
        return jobRepo.findAll();
    }

    @Override
    public Job getJobById(Long id) {
        try {
            Job job= jobRepo.getJobById(id);
            Integer view= job.getViews() +1;
            jobRepo.updateView(id, view);
            return job;
        }catch (Exception e){
          log.error("failed get by id: " + id);
            return null;
        }
    }

    @Override
    public List<Job> getListNewJobs(Integer numberDate, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);

        return jobRepo.getListNewJobs(numberDate,  pageable);
    }

    @Override
    public List<Job> getListJobsHightSalary(Integer salary, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return jobRepo.getListJobsHightSalary(salary,  pageable);
    }

    @Override
    public List<Job> getListJobDeadLine(Integer numberDate, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return jobRepo.getListJobsDeadLine(numberDate, pageable);
    }


}
