package com.itsol.recruit_managerment.controller;

import com.itsol.recruit_managerment.model.Company;
import com.itsol.recruit_managerment.service.impl.CompanyServicempl;
import com.itsol.recruit_managerment.vm.CompanyVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/company")
public class CompanyController {
    @Autowired
    private CompanyServicempl companyServicempl;

    @GetMapping("/get")
    List<Company> getAllCompany() {
        return companyServicempl.getAllCompany();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateCompany(@RequestBody CompanyVM companyVM ,@PathVariable Long id){
        try {
            companyServicempl.update(companyVM,id);
            return ResponseEntity.ok().body(companyVM);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("fail to update company");
        }
    }


}
