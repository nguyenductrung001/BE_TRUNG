package com.itsol.recruit_managerment.security;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itsol.recruit_managerment.dto.AuthenDTO;
import com.itsol.recruit_managerment.model.OTP;
import com.itsol.recruit_managerment.service.UserService;
import com.itsol.recruit_managerment.service.impl.UserServiceimpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AuthenFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private String SECRET_KEY = "secret";
    @Autowired
    UserService appUserService;

    public AuthenFilter(AuthenticationManager authenticationManager, UserServiceimpl appUserService) {
        this.authenticationManager = authenticationManager;
        this.appUserService = appUserService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
       try {
           AuthenDTO usernamePassword = new ObjectMapper().readValue(request.getInputStream(), AuthenDTO.class);
           UserDetails userDetails = appUserService.loadUserByUsername(usernamePassword.getUsername());
           if(!userDetails.isAccountNonLocked()){
               response.setHeader("error", "Account is not activated");
               com.itsol.recruit_managerment.model.User user = appUserService.getUser(usernamePassword.getUsername());
//               com.itsol.recruit_managerment.model.User user1 = appUserService.getUser(usernamePassword.getUsername());
               OTP newOTP = appUserService.retrieveNewOTP(user);
               new ObjectMapper().writeValue(response.getOutputStream(), newOTP.getCode());
           }
           UsernamePasswordAuthenticationToken authenticationToken =
                   new UsernamePasswordAuthenticationToken(usernamePassword.getUsername(), usernamePassword.getPassword());
           return authenticationManager.authenticate(authenticationToken);
       }catch (IOException ex){
           throw new RuntimeException(ex);
       }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User userDetails = (User) authResult.getPrincipal();
        com.auth0.jwt.algorithms.Algorithm algorithm = Algorithm.HMAC256("secret".getBytes()); // truongbb - secret key không nên fix cứng như này, nên mã hóa 1 lớp và để ở config
        String token = JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 20 * 60 * 1000)) // truongbb - nên để ở config yml
                .withClaim("roles", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        Map<String, String> tokenObj = new HashMap<>();
        tokenObj.put("token", token);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokenObj);
    }
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }
    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 20 * 60 * 1000))
                .setIssuer("Xixon-Knight")
                .setHeaderParam("tokenType", "Bearer ")
                .setAudience("You")
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }
}
