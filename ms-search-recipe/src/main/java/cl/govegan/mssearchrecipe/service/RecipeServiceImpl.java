package cl.govegan.mssearchrecipe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cl.govegan.mssearchrecipe.models.Recipe;
import cl.govegan.mssearchrecipe.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {
   
   @Autowired
   private RecipeRepository recipeRepository;

   @Override
   public Page<Recipe> findAll(Pageable pageable) {
      return recipeRepository.findAll(pageable);
   }

   @Override
   public List<Recipe> findByTitleContaining(String keywords) {
      return recipeRepository.findByTitleContaining(keywords);
   }
   
}
