package com.itsol.recruit_managerment.controller;

import com.itsol.recruit_managerment.model.JobPosition;
import com.itsol.recruit_managerment.service.impl.JobPositionimpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user/jobpposition")
@AllArgsConstructor
public class JobPositionController {

    JobPositionimpl jobPositionimpl;

    @GetMapping()
    public List<JobPosition> getJobPosition(){
        return jobPositionimpl.getAll();
    }

    @PostMapping()
    public ResponseEntity<Object> add(@Valid @RequestBody JobPosition job_position) {
        try {

            jobPositionimpl.save(job_position);

            return  ResponseEntity.ok().body(job_position);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("failed to create academic_level");
        }
    }

    @GetMapping("/{id}")
    public JobPosition getJobPositionById(@PathVariable Long id){

        return jobPositionimpl.getById(id);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Object> deleteJobPosition( @PathVariable Long id){
        jobPositionimpl.deleteById(id);
        return ResponseEntity.badRequest().body("abc");
    }
}
