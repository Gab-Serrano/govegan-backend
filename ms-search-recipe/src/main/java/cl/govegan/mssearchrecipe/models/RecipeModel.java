package cl.govegan.mssearchrecipe.models;

import org.springframework.data.mongodb.core.mapping.Document;

import cl.govegan.mssearchrecipe.models.recipefields.NutritionalInfo;
import cl.govegan.mssearchrecipe.models.recipefields.RecipeData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "recipes")
public class RecipeModel {
      private String title;
      private String uri;
      private String image;
      private RecipeData recipeData;
      private String[] specialNeeds;
      private NutritionalInfo nutritionalInfo;
}
