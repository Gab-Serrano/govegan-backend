package cl.govegan.mssearchrecipe.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.govegan.mssearchrecipe.models.Recipe;

@Service
public interface RecipeService {
   
   List<Recipe> findByTitleContaining(String keywords);

}
