package cl.govegan.msauth.auth.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import cl.govegan.msauth.user.config.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {

   @Value("${jwt.secret}")
   private String jwtSecret;

   @Value("${jwt.expiration}")
   private Long jwtExpirationInMs;

   public String generateToken(UserDetails userDetails) {

      UserDetailsImpl userDetailsImpl = (UserDetailsImpl) userDetails;

      Map<String, Object> extraClaims = new HashMap<>();
      extraClaims.put("username", userDetailsImpl.getUsername());
      extraClaims.put("role", userDetailsImpl.getAuthorities());
      extraClaims.put("id", userDetailsImpl.getId() );

      return Jwts.builder()
            .claims(extraClaims)
            .subject(userDetails.getUsername())
            .issuedAt(new Date())
            .expiration(new Date(new Date().getTime() + jwtExpirationInMs))
            .signWith(getKey())
            .compact();
   }

   private SecretKey getKey() {
      byte[] apiKeySecretBytes = Decoders.BASE64.decode(jwtSecret);
      return Keys.hmacShaKeyFor(apiKeySecretBytes);
   }

   public String getUsernameFromToken(String token) {
      return getClaim(token, Claims::getSubject);
   }

   public boolean isTokenValid(String token, UserDetails userDetails) {
      final String username = getUsernameFromToken(token);
      return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

   private Claims getAllClaims (String token) {

      return Jwts
         .parser()
         .verifyWith(getKey())
         .build()
         .parseSignedClaims(token)
         .getPayload();
   }

   public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {

      final Claims claims = getAllClaims(token);
      return claimsResolver.apply(claims);
   }

   private Date getExpirationDateFromToken(String token) {
      return getClaim(token, Claims::getExpiration);
   }

   private boolean isTokenExpired(String token) {
      final Date expiration = getExpirationDateFromToken(token);
      return expiration.before(new Date());
   }

}
