package com.itsol.recruit_managerment.repositories;

import com.itsol.recruit_managerment.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends JpaRepository<Company,Long> {
    Company getCompanyById(Long id);
}
