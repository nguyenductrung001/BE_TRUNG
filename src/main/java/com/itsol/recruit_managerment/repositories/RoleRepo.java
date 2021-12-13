package com.itsol.recruit_managerment.repositories;

import com.itsol.recruit_managerment.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String roleName);
}
