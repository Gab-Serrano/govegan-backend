package cl.govegan.msauth.user.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import cl.govegan.msauth.user.models.User;

public interface UserRepository extends MongoRepository<User, String> {

   Optional<User> findByUsername(String username);

}
