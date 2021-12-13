package com.itsol.recruit_managerment.repositories;

import com.itsol.recruit_managerment.model.TokenAuthen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenAuthen, Long> {
    TokenAuthen findByToken(String token);
}
