package cl.govegan.mssearchfood.services.foodservices;

import org.springframework.stereotype.Service;

import cl.govegan.mssearchfood.models.food.Food;
import cl.govegan.mssearchfood.utils.requests.food.FoodRequest;

@Service
public class FoodConverter {

   public Food toFood(FoodRequest foodRequest) {
      return Food.builder()
            .name(foodRequest.getName())
            .waterPercentage(Double.parseDouble(foodRequest.getWaterPercentage()))
            .caloriesKcal(Double.parseDouble(foodRequest.getCaloriesKcal()))
            .proteinG(Double.parseDouble(foodRequest.getProteinG()))
            .totalFatG(Double.parseDouble(foodRequest.getTotalFatG()))
            .carbohydratesG(Double.parseDouble(foodRequest.getCarbohydratesG()))
            .dietaryFiberG(Double.parseDouble(foodRequest.getDietaryFiberG()))
            .calciumMg(Double.parseDouble(foodRequest.getCalciumMg()))
            .phosphorusMg(Double.parseDouble(foodRequest.getPhosphorusMg()))
            .ironMg(Double.parseDouble(foodRequest.getIronMg()))
            .thiamineMg(Double.parseDouble(foodRequest.getThiamineMg()))
            .riboflavinMg(Double.parseDouble(foodRequest.getRiboflavinMg()))
            .niacinMg(Double.parseDouble(foodRequest.getNiacinMg()))
            .vitaminCmg(Double.parseDouble(foodRequest.getVitaminCmg()))
            .vitaminAEquivRetinolMcg(Double.parseDouble(foodRequest.getVitaminAEquivRetinolMcg()))
            .monounsaturatedFatG(Double.parseDouble(foodRequest.getMonounsaturatedFatG()))
            .polyunsaturatedFatG(Double.parseDouble(foodRequest.getPolyunsaturatedFatG()))
            .saturatedFatG(Double.parseDouble(foodRequest.getSaturatedFatG()))
            .cholesterolMg(Double.parseDouble(foodRequest.getCholesterolMg()))
            .potassiumMg(Double.parseDouble(foodRequest.getPotassiumMg()))
            .sodiumMg(Double.parseDouble(foodRequest.getSodiumMg()))
            .zincMg(Double.parseDouble(foodRequest.getZincMg()))
            .magnesiumMg(Double.parseDouble(foodRequest.getMagnesiumMg()))
            .vitaminB6Mg(Double.parseDouble(foodRequest.getVitaminB6Mg()))
            .vitaminB12Mcg(Double.parseDouble(foodRequest.getVitaminB12Mcg()))
            .folicAcidMcg(Double.parseDouble(foodRequest.getFolicAcidMcg()))
            .folateEquivFDMcg(Double.parseDouble(foodRequest.getFolateEquivFDMcg()))
            .edibleFractionPercentage(Double.parseDouble(foodRequest.getEdibleFractionPercentage()))
            .build();
   }

}
