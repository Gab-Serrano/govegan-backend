package cl.govegan.mssearchrecipe.models.recipefields;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeData {
   private String difficulty;
   private String yield;
   private String preparationTime;
   private String cookingTime;
}