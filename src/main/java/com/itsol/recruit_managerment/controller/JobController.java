package com.itsol.recruit_managerment.controller;

import com.itsol.recruit_managerment.model.Job;
import com.itsol.recruit_managerment.service.JobService;
import com.itsol.recruit_managerment.utils.CommonConst;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/job")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class JobController {

    JobService jobService;

    @CrossOrigin
    @GetMapping("/{id}")
    public Job getJobDetail(@PathVariable("id") Long id) {
        return jobService.getJobById(id);
    }


    /*
    get listjob theo tieu chi
    -	Việc làm mới tuyển (là các job mới được published từ 0 tới 7 ngày).
    -	Việc làm tuyển gấp (là các việc làm sắp hết thời hạn tuyển dụng nhưng chưa tuyển đủ người).
    -	Việc làm lương cao (là các job đang tuyển có mức lương từ 18 triệu trở lên)
     */
    @CrossOrigin
    @GetMapping("/new-job/{numberdate}/{page}/{size}")
    public List<Job> getAllJobWithNewJob(@PathVariable("numberdate") Integer nunberDate, @PathVariable("page") Integer page
            , @PathVariable("size") Integer size) {
        return jobService.getListNewJobs(nunberDate, page, size);
    }

    @CrossOrigin
    @GetMapping("/hight-salary/{page}/{size}")
    public List<Job> getAllJobWithHigthSalary(@RequestParam() Integer salary, @PathVariable("page") Integer page
            , @PathVariable("size") Integer size) {
        return jobService.getListNewJobs(salary, page, size);
    }

    /*
     Danh sach cong viec dang tuyen gop api vao
       get listjob theo tieu chi
       -	Việc làm mới tuyển (là các job mới được published từ 0 tới 7 ngày).
       -	Việc làm tuyển gấp (là các việc làm sắp hết thời hạn tuyển dụng nhưng chưa tuyển đủ người).
       -	Việc làm lương cao (là các job đang tuyển có mức lương từ 18 triệu trở lên)
    */
    @CrossOrigin
    @GetMapping("/home-page/{page}/{size}")
    public List<Job> getListJobWithConditions(
            @RequestParam("modjob") Integer modJob
            , @PathVariable("page") Integer page
            , @PathVariable("size") Integer size) {

        /**
         * truongbb - hàm này đang có vấn đề:
         *      - 3 hàm này có thể viết thành 1 hàm duy nhất
         *      - những con số điều kiện search như nunberDate, salary, deadLine ==> đưa vào constant hoặc config
         */
        // get job mowis tuyen
        if (modJob == 0) {
            Integer nunberDate = CommonConst.DAY_OF_NEW_JOB;
            return jobService.getListNewJobs(nunberDate, page, size);
        } else if (modJob == 1) {
            // get job luong cao
            Integer salary = CommonConst.HIGHT_SALARY;
            return jobService.getListJobsHightSalary(salary, page, size);
        } else if (modJob == 2) {
            // get job deadline = 3 ngay
            Integer deadLine = CommonConst.DAY_OF_NEW_JOB;
            return jobService.getListJobDeadLine(deadLine, page, size);
        } else {
            return null;
        }

    }

    @CrossOrigin
    @GetMapping()
    public List<Job> getJob() {
        return jobService.getAllJob();
    }
}
