package cl.govegan.mssearchfood.models.food;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
@Document(collection = "foods")
public class Food {
    @Id
    private String id;
    private String foodName;
    private double water;
    private double protein;
    private double fat;
    private double fiber;
    private double totalCarbs;
    private double availableCarbs;
    private double calories;
    private double saturatedFat;
    private double monounsaturatedFat;
    private double polyunsaturatedFat;
    private double cholesterol;
    private double sodium;
    private double potassium;
    private double calcium;
    private double phosphorus;
    private double iron;
    private double zinc;
    private double vitaminA;
    private double betaCarotene;
    private double thiamine;
    private double riboflavin;
    private double niacin;
    private double vitaminC;
}
