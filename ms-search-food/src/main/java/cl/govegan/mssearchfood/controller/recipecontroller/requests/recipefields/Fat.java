package cl.govegan.mssearchrecipe.controller.requests.recipefields;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fat {
   private String kcal;
   private String gr;
   private String percent;
}
