package cl.govegan.msauthuser.user.models;

import java.util.ArrayList;

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
   private ArrayList<String> allergies;
   private ArrayList<String> favoriteFoods;
   private ArrayList<String> unwantedFoods;
   private ArrayList<String> favoriteRecipes;
   private int caloriesPerDay;
   private double waterPerDay;
   private String title;



}
