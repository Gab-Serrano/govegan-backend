package cl.govegan.msauthuser.auth.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import cl.govegan.msauthuser.auth.models.RefreshToken;

public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByUsername(String username);
}
