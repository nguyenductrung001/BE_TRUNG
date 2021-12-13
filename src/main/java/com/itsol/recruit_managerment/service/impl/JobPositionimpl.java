package com.itsol.recruit_managerment.service.impl;

import com.itsol.recruit_managerment.model.JobPosition;
import com.itsol.recruit_managerment.repositories.JobPositionRepo;
import com.itsol.recruit_managerment.service.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class JobPositionimpl implements Task<JobPosition> {

    JobPositionRepo jobPositionRepo;

    @Override
    public List<JobPosition> getAll() {
        return jobPositionRepo.findAll();
    }

    @Override
    public JobPosition save(JobPosition job_position) {
        return jobPositionRepo.save(job_position);
    }

    @Override
    public JobPosition getById(Long id) {
        return jobPositionRepo.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        jobPositionRepo.delete(id);
    }
}
