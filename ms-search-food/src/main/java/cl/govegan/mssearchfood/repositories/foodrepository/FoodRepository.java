package cl.govegan.mssearchfood.repositories.foodrepository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.lang.NonNull;

import cl.govegan.mssearchfood.models.food.Food;

@RepositoryRestResource(collectionResourceRel = "foods", path = "foods")
public interface FoodRepository extends MongoRepository<Food, String> {

   @Override
   @NonNull
   Page<Food> findAll(@NonNull Pageable pageable);

   @Query("{ 'foodName' : { $regex: ?0, $options: 'i' } }")
   Page<Food> findByFoodNameContaining(@NonNull String keywords, Pageable pageable);

   @NonNull
   @Override
   Optional<Food> findById(@NonNull String id);
   
}
