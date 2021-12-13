package com.itsol.recruit_managerment.service;

import com.itsol.recruit_managerment.model.Job;
import org.springframework.data.domain.Page;

import java.util.List;

public interface JobService {

    List<Job> getAllJob();

    Job getJobById(Long id);

    List<Job> getListNewJobs(Integer numberDate, Integer page, Integer size);

    List<Job> getListJobsHightSalary(Integer salary, Integer page, Integer size);

    List<Job> getListJobDeadLine(Integer numberDate, Integer page, Integer size);
}