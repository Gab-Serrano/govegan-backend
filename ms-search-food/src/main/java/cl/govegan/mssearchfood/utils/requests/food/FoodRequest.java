package cl.govegan.mssearchfood.utils.requests.food;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class FoodRequest {

   @Nonnull
   String name;
   String waterPercentage;
   String caloriesKcal;
   String proteinG;
   String totalFatG;
   String carbohydratesG;
   String dietaryFiberG;
   String calciumMg;
   String phosphorusMg;
   String ironMg;
   String thiamineMg;
   String riboflavinMg;
   String niacinMg;
   String vitaminCmg;
   String vitaminAEquivRetinolMcg;
   String monounsaturatedFatG;
   String polyunsaturatedFatG;
   String saturatedFatG;
   String cholesterolMg;
   String potassiumMg;
   String sodiumMg;
   String zincMg;
   String magnesiumMg;
   String vitaminB6Mg;
   String vitaminB12Mcg;
   String folicAcidMcg;
   String folateEquivFDMcg;
   String edibleFractionPercentage;
}
