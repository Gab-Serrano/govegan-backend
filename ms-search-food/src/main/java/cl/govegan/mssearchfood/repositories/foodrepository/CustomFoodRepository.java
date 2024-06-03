package cl.govegan.mssearchfood.repositories.foodrepository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cl.govegan.mssearchfood.models.food.Food;
import cl.govegan.mssearchfood.models.food.FoodCategory;

public interface CustomFoodRepository {
    Page<Food> findAllWithCategory(Pageable pageable);
    Page<Food> findByNameContainingWithCategory(String keywords, Pageable pageable);
    Optional<Food> findByIdWithCategory(String id);
    List<FoodCategory> findAllCategories();
}
