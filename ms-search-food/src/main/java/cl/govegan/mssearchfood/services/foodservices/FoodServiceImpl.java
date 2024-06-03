package cl.govegan.mssearchfood.services.foodservices;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cl.govegan.mssearchfood.models.food.Food;
import cl.govegan.mssearchfood.repositories.foodrepository.FoodRepository;
import cl.govegan.mssearchfood.utils.requests.food.FoodRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

   private final FoodRepository foodRepository;
   private final FoodConverter foodConverter;

   @Override
   public Page<Food> findAll(Pageable pageable) {
      return foodRepository.findAllWithCategory(pageable);
   }

   @Override
   public Page<Food> findByFoodNameContaining(String keywords, Pageable pageable) {
      return foodRepository.findByNameContainingWithCategory(keywords, pageable);
   }

   @Override
   public Optional<Food> findById(String id) {
      return foodRepository.findByIdWithCategory(id);
   }

   @Override
   public Food saveFood(FoodRequest foodRequest) {
      Food foodToSave = foodConverter.toFood(foodRequest);
      return foodRepository.save(foodToSave);
   }

   @Override
   public void deleteById(String id) {
      foodRepository.deleteById(id);
   }

}
