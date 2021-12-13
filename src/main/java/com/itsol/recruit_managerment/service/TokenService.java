package com.itsol.recruit_managerment.service;

import com.itsol.recruit_managerment.model.TokenAuthen;
import com.itsol.recruit_managerment.repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TokenService{

    @Autowired
    private TokenRepository tokenRepository;

    public TokenAuthen createToken(TokenAuthen token) {
        return tokenRepository.saveAndFlush(token);
    }

    public TokenAuthen findByToken(String token) {
        return tokenRepository.findByToken(token);
    }
}
