package com.itsol.recruit_managerment.repositories;

import com.itsol.recruit_managerment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IUserRespository extends JpaRepository<User,Long> {
//    List<User> findAll();
    User findByUserName(String userName);

    User findByEmail(String email);

    User getUserById(Long id);
    @Query(value = "from Users u where u.fullName like %:fullName%")
    List<User> findByFullName(@Param("fullName") String fullName);
    @Query(nativeQuery = true,value = "select * from users u where u.id in (select USERS_ID from USERS_ROLES r where r.ROLES_ID = 3)")
    List<User> getAllJE();

}
