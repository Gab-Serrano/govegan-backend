package cl.govegan.mssearchfood.services.foodservices;

import org.springframework.stereotype.Service;

import cl.govegan.mssearchfood.models.food.Food;
import cl.govegan.mssearchfood.utils.requests.food.FoodRequest;

@Service
public class FoodConverter {

   public Food toFood (FoodRequest foodRequest) {
      return Food.builder()
            .foodName(foodRequest.getFoodName())
            .water(Double.parseDouble(foodRequest.getWater()))
            .protein(Double.parseDouble(foodRequest.getProtein()))
            .fat(Double.parseDouble(foodRequest.getFat()))
            .fiber(Double.parseDouble(foodRequest.getFiber()))
            .totalCarbs(Double.parseDouble(foodRequest.getTotalCarbs()))
            .availableCarbs(Double.parseDouble(foodRequest.getAvailableCarbs()))
            .calories(Double.parseDouble(foodRequest.getCalories()))
            .saturatedFat(Double.parseDouble(foodRequest.getSaturatedFat()))
            .monounsaturatedFat(Double.parseDouble(foodRequest.getMonounsaturatedFat()))
            .polyunsaturatedFat(Double.parseDouble(foodRequest.getPolyunsaturatedFat()))
            .cholesterol(Double.parseDouble(foodRequest.getCholesterol()))
            .sodium(Double.parseDouble(foodRequest.getSodium()))
            .potassium(Double.parseDouble(foodRequest.getPotassium()))
            .calcium(Double.parseDouble(foodRequest.getCalcium()))
            .phosphorus(Double.parseDouble(foodRequest.getPhosphorus()))
            .iron(Double.parseDouble(foodRequest.getIron()))
            .zinc(Double.parseDouble(foodRequest.getZinc()))
            .vitaminA(Double.parseDouble(foodRequest.getVitaminA()))
            .betaCarotene(Double.parseDouble(foodRequest.getBetaCarotene()))
            .thiamine(Double.parseDouble(foodRequest.getThiamine()))
            .riboflavin(Double.parseDouble(foodRequest.getRiboflavin()))
            .niacin(Double.parseDouble(foodRequest.getNiacin()))
            .vitaminC(Double.parseDouble(foodRequest.getVitaminC()))
            .build();
   }
   
}
