package cl.govegan.mssearchfood.services.recipeservices;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cl.govegan.mssearchrecipe.models.Recipe;

@Service
public interface RecipeService {
   
   Page<Recipe> findAll(Pageable pageable);
   List<Recipe> findByTitleContaining(String keywords);

}
