package cl.govegan.msauthuser.auth.requests;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

   String username;
   String password;

   public Authentication toAuthentication() {
      return new UsernamePasswordAuthenticationToken(username, password);
   }

}
