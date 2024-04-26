package cl.govegan.mssearchrecipe.controller.requestclases;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import cl.govegan.mssearchrecipe.controller.requestclases.recipefields.NutritionalInfo;
import cl.govegan.mssearchrecipe.controller.requestclases.recipefields.RecipeData;
import cl.govegan.mssearchrecipe.controller.requestclases.recipefields.YieldPerAge;
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
