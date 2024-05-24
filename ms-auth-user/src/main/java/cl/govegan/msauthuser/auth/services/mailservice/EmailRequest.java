package cl.govegan.msauthuser.auth.services.mailservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmailRequest {

   private String to;
   private String subject;
   private String body;
   private String name;
   
}
