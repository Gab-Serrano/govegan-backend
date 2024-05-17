package cl.govegan.msauthuser.user.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Profile {

   private String name;
   private String profilePicture;
   private int age;
   private Gender gender;
   private double weight;
   private double height;
   private String city;
   private String country;
   private String[] allergies;
   private String[] favoriteFoods;
   private String[] unwantedFoods;
   private String[] favoriteRecipes;
   private int caloriesPerDay;
   private double waterPerDay;
   private String title;



}
