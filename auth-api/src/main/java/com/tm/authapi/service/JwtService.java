package com.tm.authapi.service;

import com.tm.authapi.exception.AuthenticationException;
import com.tm.authapi.exception.ValidationException;
import com.tm.authapi.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${app.token.secret-key}")
    private String secretKey;

    private static final Integer HALF_HOUR_IN_MINUTES = 30;
    private static final String EMPTY_SPACE = " ";
    private static final Integer TOKEN_INDEX = 1;

    public String createToken(User user) {
        var data = new HashMap<String, String>();
        data.put("id", user.getId().toString());
        data.put("username", user.getUsername());

        return Jwts
                .builder()
                .claims(data)
                .expiration(generateExpiresAt())
                .signWith(generateSign())
                .compact();
    }

    private Date generateExpiresAt(){
        return Date.from(
                LocalDateTime
                        .now()
                        .plusMinutes(HALF_HOUR_IN_MINUTES)
                        .atZone(ZoneId.systemDefault()).toInstant()
        );
    }

    private SecretKey generateSign(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public void validateAccessToken (String token) {
        var accessToken = extractToken(token);
        try {
            Jwts
                    .parser()
                    .verifyWith(generateSign())
                    .build()
                    .parseSignedClaims(accessToken)
                    .getPayload();


        } catch(Exception e){
            throw new AuthenticationException("Invalid token " + e.getMessage());
        }
    }

    private String extractToken(String token){
        if(ObjectUtils.isEmpty(token)){
            throw new ValidationException("The access was not informed!");
        }
        if (token.contains(EMPTY_SPACE)){
            return token.split(EMPTY_SPACE)[TOKEN_INDEX];
        }
        return token;
    }

}
