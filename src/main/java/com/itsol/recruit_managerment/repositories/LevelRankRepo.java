package com.itsol.recruit_managerment.repositories;

import com.itsol.recruit_managerment.model.LevelRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface LevelRankRepo extends JpaRepository<LevelRank,Long> {


    @Query(value = "from level_rank u where u.id = :id ")
    LevelRank getById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value=" update level_rank u set u.isDelete = 0 where u.id=:id")
    void delete(@Param("id") Long id);

}
