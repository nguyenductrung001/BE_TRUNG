package com.itsol.recruit_managerment.service;

import com.itsol.recruit_managerment.model.Company;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CompanyService {
    List<Company> getAllCompany();

}
