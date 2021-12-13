package com.itsol.recruit_managerment.service.impl;

import com.itsol.recruit_managerment.model.LevelRank;
import com.itsol.recruit_managerment.repositories.LevelRankRepo;
import com.itsol.recruit_managerment.service.Task;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class LevelRankimpl implements Task<LevelRank> {

    @Autowired
    LevelRankRepo levelRankRepo;

    @Override
    public List<LevelRank> getAll() {
        return  levelRankRepo.findAll();
    }

    @Override
    public LevelRank save(LevelRank level_rank) {
        return levelRankRepo.save(level_rank);
    }

    @Override
    public LevelRank getById(Long id) {
        return levelRankRepo.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        levelRankRepo.delete(id);
    }
}
