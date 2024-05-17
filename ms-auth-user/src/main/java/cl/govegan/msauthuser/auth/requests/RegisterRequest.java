package cl.govegan.msauthuser.auth.requests;

import com.mongodb.lang.NonNull;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
      @NotNull @NonNull @Size(min = 4, max = 20)
      String username;

      @NotNull @Size(min = 8)
      String password;

      @NotNull @NonNull @Email
      String email;

      /* User Profile */
      String name;
      String profilePicture;

      @Min(0) @Max(99)
      int age;

      String gender;

      @Min(0)
      double weight;

      @Min(0)
      double height;

      String city;
      String country;
      String[] allergies;
      String[] favoriteFoods;
      String[] unwantedFoods;
      String[] favoriteRecipes;

      @Min(0)
      int caloriesPerDay;

      @Min(0)
      double waterPerDay;

      String title;

}
