package Teste.de.dependencia.demo.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import Teste.de.dependencia.demo.dto.LoginUserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;
    public String generateToken(LoginUserDto dto) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer("demo")
                .withExpiresAt(Instant.now().plusSeconds(108000))
                .withSubject(dto.email())
                .sign(algorithm);
    }
    public String validateToken(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("demo")
                    .build()
                    .verify(token)
                    .getSubject();


        }catch (JWTVerificationException exception){
            return null;
        }
    }

}
