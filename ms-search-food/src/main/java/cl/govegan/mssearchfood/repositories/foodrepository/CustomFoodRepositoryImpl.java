package cl.govegan.mssearchfood.repositories.foodrepository;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import cl.govegan.mssearchfood.models.food.Food;
import cl.govegan.mssearchfood.models.food.FoodCategory;

@Repository
public class CustomFoodRepositoryImpl implements CustomFoodRepository {

   @Autowired
   private MongoTemplate mongoTemplate;

   @Override
   public Page<Food> findAllWithCategory(Pageable pageable) {
      LookupOperation lookupOperation = LookupOperation.newLookup()
            .from("foodCategories") // Collection name should be "foodCategories"
            .localField("categoryId")
            .foreignField("_id")
            .as("category");

      Aggregation aggregation = Aggregation.newAggregation(
            lookupOperation,
            Aggregation.unwind("category"),
            Aggregation.project("name", "categoryId", "waterPercentage", "caloriesKcal", "proteinG", "totalFatG", "carbohydratesG", "dietaryFiberG", "calciumMg", "phosphorusMg", "ironMg", "thiamineMg", "riboflavinMg", "niacinMg", "vitaminCmg", "vitaminAEquivRetinolMcg", "monounsaturatedFatG", "polyunsaturatedFatG", "saturatedFatG", "cholesterolMg", "potassiumMg", "sodiumMg", "zincMg", "magnesiumMg", "vitaminB6Mg", "vitaminB12Mcg", "folicAcidMcg", "folateEquivFDMcg", "edibleFractionPercentage")
                  .and("category.categoryName").as("categoryName"),
            Aggregation.skip((long) pageable.getOffset()),
            Aggregation.limit(pageable.getPageSize()));

      List<Food> results = mongoTemplate.aggregate(aggregation, "foods", Food.class).getMappedResults();
      long total = mongoTemplate.count(new Query(), Food.class);
      return new PageImpl<>(results, pageable, total);
   }

   @Override
   public Page<Food> findByNameContainingWithCategory(String keywords, Pageable pageable) {
      LookupOperation lookupOperation = LookupOperation.newLookup()
            .from("foodCategories")
            .localField("categoryId")
            .foreignField("_id")
            .as("category");

      MatchOperation matchOperation = Aggregation.match(
            Criteria.where("name").regex(keywords, "i"));

      Aggregation aggregation = Aggregation.newAggregation(
            matchOperation,
            lookupOperation,
            Aggregation.unwind("category"),
            Aggregation.project("name", "categoryId", "waterPercentage", "caloriesKcal", "proteinG", "totalFatG",
                  "carbohydratesG", "dietaryFiberG", "calciumMg", "phosphorusMg", "ironMg", "thiamineMg",
                  "riboflavinMg", "niacinMg", "vitaminCmg", "vitaminAEquivRetinolMcg", "monounsaturatedFatG",
                  "polyunsaturatedFatG", "saturatedFatG", "cholesterolMg", "potassiumMg", "sodiumMg", "zincMg",
                  "magnesiumMg", "vitaminB6Mg", "vitaminB12Mcg", "folicAcidMcg", "folateEquivFDMcg",
                  "edibleFractionPercentage")
                  .and("category.categoryName").as("categoryName"),
            Aggregation.skip((long) pageable.getOffset()),
            Aggregation.limit(pageable.getPageSize()));

      List<Food> results = mongoTemplate.aggregate(aggregation, "foods", Food.class).getMappedResults();
      long total = mongoTemplate.count(new Query(Criteria.where("name").regex(keywords, "i")), Food.class);
      return new PageImpl<>(results, pageable, total);
   }

   @Override
   public Optional<Food> findByIdWithCategory(String id) {
      LookupOperation lookupOperation = LookupOperation.newLookup()
            .from("foodCategories")
            .localField("categoryId")
            .foreignField("_id")
            .as("category");

      MatchOperation matchOperation = Aggregation.match(
            Criteria.where("_id").is(new ObjectId(id)));

      Aggregation aggregation = Aggregation.newAggregation(
            matchOperation,
            lookupOperation,
            Aggregation.unwind("category"),
            Aggregation.project("name", "categoryId", "waterPercentage", "caloriesKcal", "proteinG", "totalFatG",
                  "carbohydratesG", "dietaryFiberG", "calciumMg", "phosphorusMg", "ironMg", "thiamineMg",
                  "riboflavinMg", "niacinMg", "vitaminCmg", "vitaminAEquivRetinolMcg", "monounsaturatedFatG",
                  "polyunsaturatedFatG", "saturatedFatG", "cholesterolMg", "potassiumMg", "sodiumMg", "zincMg",
                  "magnesiumMg", "vitaminB6Mg", "vitaminB12Mcg", "folicAcidMcg", "folateEquivFDMcg",
                  "edibleFractionPercentage")
                  .and("category.categoryName").as("categoryName"));

      List<Food> results = mongoTemplate.aggregate(aggregation, "foods", Food.class).getMappedResults();
      return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
   }

    @Override
    public List<FoodCategory> findAllCategories() {
        return mongoTemplate.findAll(FoodCategory.class);
    }
}