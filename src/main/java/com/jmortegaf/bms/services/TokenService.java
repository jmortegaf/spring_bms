package com.jmortegaf.bms.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jmortegaf.bms.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration.time}")
    private Long jwtExpirationTime;
    @Value("${jwt.token.issuer}")
    private String jwtTokenIssuer;

    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            return JWT.create()
                    .withIssuer(jwtTokenIssuer)
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        }catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    public String getSubject(String token) {
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(jwtTokenIssuer)
                    .build();
            return verifier.verify(token).getSubject();
        }catch (JWTVerificationException exception){
            System.out.println(exception.getMessage());
            throw new RuntimeException();
        }
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(jwtExpirationTime).toInstant(ZoneOffset.of("-03:00"));
    }

}
