package cl.govegan.mssearchfood.services.foodservices;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cl.govegan.mssearchfood.models.food.Food;
import cl.govegan.mssearchfood.utils.requests.food.FoodRequest;

@Service
public interface FoodService {

   public Page<Food> findAll(Pageable pageable);

   public Page<Food> findByFoodNameContaining(String keywords, Pageable pageable);

   public Optional<Food> findById(String id);

   public Food saveFood (FoodRequest foodRequest);

   public void deleteById(String id);
    
}