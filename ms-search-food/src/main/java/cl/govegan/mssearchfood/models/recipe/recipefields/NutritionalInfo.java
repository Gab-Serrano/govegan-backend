package cl.govegan.mssearchfood.models.recipe.recipefields;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NutritionalInfo {
   private String totalKcal;
   private Fat fat;
   private Carbohydrates carbohydrates;
   private Protein protein;
   private AdditionalInfo additionalInfo; 
}
