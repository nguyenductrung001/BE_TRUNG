package com.itsol.recruit_managerment.service.impl;

import com.itsol.recruit_managerment.model.AcademicLevel;
import com.itsol.recruit_managerment.repositories.AcademiclevelRepo;
import com.itsol.recruit_managerment.service.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class Academiclevelimpl implements Task<AcademicLevel> {

    @Autowired
    AcademiclevelRepo academiclevelRepo;

    @Override
    public List<AcademicLevel> getAll() {
        return academiclevelRepo.findAll();
    }

    @Override
    public AcademicLevel save(AcademicLevel academic_level) {
        return academiclevelRepo.save(academic_level);
    }

    @Override
    public AcademicLevel getById(Long id) {
        return academiclevelRepo.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        academiclevelRepo.deleteById(id);
    }


}
