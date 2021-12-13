package com.itsol.recruit_managerment.repositories;

import com.itsol.recruit_managerment.model.Job;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.itsol.recruit_managerment.model.Profiles;
import com.itsol.recruit_managerment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

import java.util.List;

@EnableJpaRepositories
public interface JobRepo extends JpaRepository<Job,Long> {


/*
api danh sach viec lam mới
 */
    @Query(value = "select Jobs.* from status_job\n" +
            "    INNER JOIN Jobs ON JOBS.status_job_id = status_job.id\n" +
            "    where JOBs.is_delete = 0 and  (SYSDATE - JOBs.create_date) < :numberDate and (\n" +
            "            status_job.NAME NOT LIKE  'J_STOP' AND  status_job.NAME NOT LIKE  'J_WAIT' AND status_job.NAME NOT LIKE  'J_CLOSED')", nativeQuery = true)
    List<Job> getListNewJobs(@Param("numberDate") Integer numberDate, Pageable pageable);

    /*
    api danh sách việc làm lương cao từ 18 triệu trở lên.
     */
    @Query(value = "select Jobs.* from status_job\n" +
            "    INNER JOIN Jobs ON JOBS.status_job_id = status_job.id\n" +
            "    where JOBs.is_delete = 0 and  jobs.salary >= :salary and (\n" +
            "            status_job.NAME NOT LIKE  'J_STOP' AND  status_job.NAME NOT LIKE  'J_WAIT' AND status_job.NAME NOT LIKE  'J_CLOSED')", nativeQuery = true)
    List<Job> getListJobsHightSalary(@Param("salary") Integer salary, Pageable pageable);

    @Query(value ="select jobs.*,temp.countJob as datuyen  from jobs inner join " +
        "(select jobs.id, count(jobs.id) as countJob from jobs  " +
        "inner join job_register on jobs.id  = job_register.job_id " +
        "where job_register.profile_status_id =7 and job_register.is_delete =0 and jobs.is_delete = 0 " +
        "group by jobs.id ) temp on temp.id = jobs.id where temp.countJob < jobs.qty_person  and current_date - jobs.due_date <= :numberDay", nativeQuery = true)
    List<Job> getListJobsDeadLine(@Param("numberDay") Integer numberDay, Pageable pageable);

  //kiet
    @Query(value = "from Jobs u where u.id = :id ")
    Job getJobById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "update Jobs u set u.views=:view where u.id = :id")
    void updateView(@Param("id") Long id, Integer view);

}
