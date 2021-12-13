package com.itsol.recruit_managerment.repositories;

import com.itsol.recruit_managerment.model.AcademicLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface AcademiclevelRepo extends JpaRepository<AcademicLevel, Long> {

    @Query(value = "from Academic_Level u where u.id = :id ")
    AcademicLevel getById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = " update Academic_Level u set u.isDelete = 0 where u.id=:id")
    void deleteAc(@Param("id") Long id);

}
