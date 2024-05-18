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
   String foodName;
   String water;
   String protein;
   String fat;
   String fiber;
   String totalCarbs;
   String availableCarbs;
   String calories;
   String saturatedFat;
   String monounsaturatedFat;
   String polyunsaturatedFat;
   String cholesterol;
   String sodium;
   String potassium;
   String calcium;
   String phosphorus;
   String iron;
   String zinc;
   String vitaminA;
   String betaCarotene;
   String thiamine;
   String riboflavin;
   String niacin;
   String vitaminC;
}
