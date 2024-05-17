package cl.govegan.msauthuser.auth.models;

import java.util.Date;

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
@Document(collection = "refreshTokens")
public class RefreshToken {

    @Id
    private String id;

    @Indexed(unique = true)
    private String token;

    private String username;

    private Date expiryDate;
}
