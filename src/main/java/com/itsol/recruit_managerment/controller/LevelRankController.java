package com.itsol.recruit_managerment.controller;
import com.itsol.recruit_managerment.model.LevelRank;
import com.itsol.recruit_managerment.service.impl.LevelRankimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user/levelrank")
public class LevelRankController {
    @Autowired
    LevelRankimpl levelRankimpl;
    @GetMapping()
    public List<LevelRank> getLevelRank(){
        return levelRankimpl.getAll();
    }

    @PostMapping()
    public ResponseEntity<Object> add(@Valid @RequestBody LevelRank level_rank) {
        try {

            levelRankimpl.save(level_rank);

            return  ResponseEntity.ok().body(level_rank);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("failed to create academic_level");
        }
    }

    @GetMapping("/{id}")
    public LevelRank getById(@PathVariable Long id){

        return levelRankimpl.getById(id);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<Object> deleteLevelRank( @PathVariable Long id){
        levelRankimpl.deleteById(id);
        return ResponseEntity.badRequest().body("abc");
    }
}
