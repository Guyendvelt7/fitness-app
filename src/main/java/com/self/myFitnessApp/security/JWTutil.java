package com.self.myFitnessApp.security;

import com.self.myFitnessApp.beans.ClientType;
import com.self.myFitnessApp.beans.UserDetails;
import com.self.myFitnessApp.exceptions.CustomExceptions;
import com.self.myFitnessApp.exceptions.ExceptionsMessages;
import com.self.myFitnessApp.repo.CoachRepo;
import com.self.myFitnessApp.repo.TraineeRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JWTutil {
    private final TraineeRepo traineeRepo;
    private final CoachRepo coachRepo;

    //type of encryption
    private String signatureAlgorithm = SignatureAlgorithm.HS256.getJcaName();
    //secret key
    private final String secretKey = "this+is+guys+fitness+app+and+web+project+secret+key";
    //private key
    private Key decodedSecretKey = new SecretKeySpec
            (Base64.getDecoder().decode(secretKey), this.signatureAlgorithm);


    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userEmail", userDetails.getEmail());
        return "Bearer " + createToken(claims, userDetails.getClientType().toString());

    }
       private String createToken(Map<String, Object> claims, String userType){
            Instant now = Instant.now();
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(userType)
                    .setIssuedAt(Date.from(now))
                    .setExpiration(Date.from(now.plus(15, ChronoUnit.MINUTES)))
                    .signWith(decodedSecretKey)
                    .compact();
        }

    private Claims extractAllClaims(String token) {
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(decodedSecretKey).build();
        return jwtParser.parseClaimsJws(token).getBody();
    }



    public String checkUser(String token, ClientType clientType) throws CustomExceptions {
        if(!isRightClientType(token, clientType)){
            throw new CustomExceptions(ExceptionsMessages.DONT_HAVE_PERMISSIONS);
        }
        Claims claims = extractAllClaims(token.replace("Bearer ", ""));
        UserDetails userDetails = new UserDetails();
        userDetails.setEmail((String) claims.get("userEmail"));
        userDetails.setClientType(clientType);
        return generateToken(userDetails);
    }


    private String extractSignature(String token){
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(decodedSecretKey).build();
        return jwtParser.parseClaimsJws(token).getSignature();
    }

    public String extractSubject(String token) {
        return extractAllClaims(token.replace("Bearer ", "")).getSubject();
    }

    public ClientType extractClientType(String token) {
        return ClientType.valueOf(extractSubject(token));
    }

    private boolean isRightClientType(String token, ClientType clientType) {
        return extractClientType(token).equals(clientType);
    }






}