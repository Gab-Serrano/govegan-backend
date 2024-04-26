package cl.govegan.mssearchrecipe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.govegan.mssearchrecipe.models.Recipe;
import cl.govegan.mssearchrecipe.repository.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {
   
   @Autowired
   private RecipeRepository recipeRepository;

   @Override
   public List<Recipe> findByTitleContaining(String keywords) {
      return recipeRepository.findByTitleContaining(keywords);
   }
   
}
