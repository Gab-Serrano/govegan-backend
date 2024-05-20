package cl.govegan.msauthuser.user.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.govegan.msauthuser.auth.requests.RegisterRequest;
import cl.govegan.msauthuser.user.models.Gender;
import cl.govegan.msauthuser.user.models.Profile;
import cl.govegan.msauthuser.user.models.Role;
import cl.govegan.msauthuser.user.models.User;

@Service
public class UserConverter {

   public User toUser(RegisterRequest registerRequest, PasswordEncoder passwordEncoder) {

      if ("".equals(registerRequest.getGender())) {
            registerRequest.setGender("OTRO");
      }

      return User.builder()
            .username(registerRequest.getUsername())
            .password(passwordEncoder.encode(registerRequest.getPassword()))
            .email(registerRequest.getEmail())
            .role(Role.USER)
            .profile(Profile.builder()
                  .name(registerRequest.getName())
                  .profilePicture(registerRequest.getProfilePicture())
                  .age(registerRequest.getAge())
                  .gender(Gender.valueOf(registerRequest.getGender().toUpperCase()))
                  .weight(registerRequest.getWeight())
                  .height(registerRequest.getHeight())
                  .city(registerRequest.getCity())
                  .country(registerRequest.getCountry())
                  .allergies(registerRequest.getAllergies())
                  .favoriteFoods(registerRequest.getFavoriteFoods())
                  .unwantedFoods(registerRequest.getUnwantedFoods())
                  .favoriteRecipes(registerRequest.getFavoriteRecipes())
                  .caloriesPerDay(registerRequest.getCaloriesPerDay())
                  .waterPerDay(registerRequest.getWaterPerDay())
                  .title(registerRequest.getTitle())
                  .build())
            .build();
   }

}
