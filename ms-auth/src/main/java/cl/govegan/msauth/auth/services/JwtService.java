package cl.govegan.msauth.auth.services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cl.govegan.msauth.user.config.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {

   @Value("${jwt.secret}")
   private String jwtSecret;

   @Value("${jwt.expiration}")
   private Long jwtExpirationInMs;

   public String generateToken(UserDetailsImpl userDetails) {

      Map<String, Object> claims = new HashMap<>();
      claims.put("username", userDetails.getUsername());
      claims.put("role", userDetails.getAuthorities());

      return Jwts.builder()
            .setClaims(claims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date(new Date().getTime() + jwtExpirationInMs))
            .signWith(getKey(), SignatureAlgorithm.HS256)
            .compact();
   }

   private Key getKey() {
      byte[] apiKeySecretBytes = Decoders.BASE64.decode(jwtSecret);
      return Keys.hmacShaKeyFor(apiKeySecretBytes);
   }
   
}
