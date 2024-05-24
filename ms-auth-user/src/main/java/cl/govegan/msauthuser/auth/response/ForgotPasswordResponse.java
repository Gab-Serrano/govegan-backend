package cl.govegan.msauthuser.auth.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ForgotPasswordResponse {

      
      private String message;
      private String token;
   
}
