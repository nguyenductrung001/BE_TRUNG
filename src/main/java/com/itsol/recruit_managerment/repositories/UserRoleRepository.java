package com.itsol.recruit_managerment.repositories;

import com.itsol.recruit_managerment.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole , UserRole.MyIdclass> {
}
