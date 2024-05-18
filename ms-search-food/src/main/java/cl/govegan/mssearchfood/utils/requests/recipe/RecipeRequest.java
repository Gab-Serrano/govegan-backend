package cl.govegan.mssearchfood.utils.requests.recipe;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import cl.govegan.mssearchfood.utils.requests.recipe.recipefields.NutritionalInfo;
import cl.govegan.mssearchfood.utils.requests.recipe.recipefields.RecipeData;
import cl.govegan.mssearchfood.utils.requests.recipe.recipefields.YieldPerAge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "recipes")
public class RecipeRequest {
      @Id private String id;
      private String title;
      private String uri;
      private String image;
      private RecipeData recipeData;
      private String[] specialNeeds;
      private NutritionalInfo nutritionalInfo;
      private YieldPerAge yieldPerAge;
      private String[] ingredientLines;
      private String[] directions;
      private String[] tags;
}
