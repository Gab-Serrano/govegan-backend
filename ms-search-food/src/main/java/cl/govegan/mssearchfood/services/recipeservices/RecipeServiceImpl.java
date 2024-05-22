package cl.govegan.mssearchfood.services.recipeservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cl.govegan.mssearchfood.models.recipe.Recipe;
import cl.govegan.mssearchfood.repositories.reciperepository.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {
   
   @Autowired
   private RecipeRepository recipeRepository;

   @Override
   public Page<Recipe> findAll(Pageable pageable) {
      return recipeRepository.findAll(pageable);
   }

   @Override
   public Page<Recipe> findByTitleContaining(String keywords, Pageable pageable) {
      return recipeRepository.findByTitleContaining(keywords, pageable);
   }

   @Override
   public Recipe findById(String id) {
      return recipeRepository.findById(id).orElse(null);
   }
   
}
