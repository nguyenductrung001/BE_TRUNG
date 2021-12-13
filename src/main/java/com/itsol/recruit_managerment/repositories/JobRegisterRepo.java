package com.itsol.recruit_managerment.repositories;

import com.itsol.recruit_managerment.model.JobRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface JobRegisterRepo extends JpaRepository<JobRegister,Long> {
}
