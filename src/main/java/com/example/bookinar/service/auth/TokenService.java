package com.example.bookinar.service.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.bookinar.entity.user.RoleEntity;
import com.example.bookinar.entity.user.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TokenService {

    private static final String ROLES = "roles";
    private static final String BEARER = "Bearer ";

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer}")
    private String issuer;

    public String generateToken(Authentication authentication) {
        try {
            UserEntity userEntity = (UserEntity) authentication.getPrincipal();
            Date expiracao = new Date(new Date().getTime() + Long.parseLong(expiration));
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withClaim("LOGIN", userEntity.getLogin())
                    .withClaim("ID", userEntity.getId())
                    .withClaim(ROLES, userEntity.getRoles().stream().map(RoleEntity::getAuthority).toList())
                    .withIssuer(issuer)
                    .withExpiresAt(expiracao)
                    .sign(algorithm);
            return BEARER + token;
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Error while generating token: ", exception);
        }
    }

    public UsernamePasswordAuthenticationToken validateToken(String token) {
        try {
            if(token == null){
                return null;
            }
            token = token.replace(BEARER, "").trim();
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT jwt =  JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token);

            Integer id = jwt.getClaim("ID").asInt();
            List<String> roles = jwt.getClaim(ROLES).asList(String.class);
            List<SimpleGrantedAuthority> rolesUser = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .toList();

            return new UsernamePasswordAuthenticationToken(id, null, rolesUser);
        } catch (JWTVerificationException exception) {
            return null;
        }
    }
}