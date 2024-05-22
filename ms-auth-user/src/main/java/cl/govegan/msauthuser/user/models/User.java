package cl.govegan.msauthuser.user.models;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users")
public class User {

   @Id
   private String id;

   @Indexed(unique = true)
   private String username;
   
   private String email;
   private String password;
   private Set<Role> roles;
   private Profile profile;
}
