package cl.govegan.mssearchfood.services.recipeservices;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cl.govegan.mssearchfood.models.recipe.Recipe;

@Service
public interface RecipeService {
   
   Page<Recipe> findAll(Pageable pageable);
   Page<Recipe> findByTitleContaining(String keywords, Pageable pageable);
   Recipe findById(String id);

}
