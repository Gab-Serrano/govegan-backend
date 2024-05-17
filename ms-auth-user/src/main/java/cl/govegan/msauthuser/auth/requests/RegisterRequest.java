package cl.govegan.msauthuser.auth.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

      /* User data */
      String username;
      String password;
      String email;

      /* User Profile */
      String name;
      String profilePicture;
      int age;
      String gender;
      double weight;
      double height;
      String city;
      String country;
      String[] allergies;
      String[] favoriteFoods;
      String[] unwantedFoods;
      String[] favoriteRecipes;
      int caloriesPerDay;
      double waterPerDay;
      String title;

}
