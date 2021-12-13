package com.itsol.recruit_managerment.service.impl;

import com.itsol.recruit_managerment.model.Profiles;
import com.itsol.recruit_managerment.model.User;
import com.itsol.recruit_managerment.repositories.ProfilesRepo;
import com.itsol.recruit_managerment.service.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfilesServiceimpl implements Task<Profiles> {
    @Autowired
    ProfilesRepo profilesRepo;

    @Override
    public List<Profiles> getAll() {
        return profilesRepo.findAll();
    }

    @Override
    public Profiles save(Profiles profiles) {
        return profilesRepo.save(profiles);
    }

    @Override
    public Profiles getById(Long id) {
        return profilesRepo.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        profilesRepo.delete(id);
    }


}
