package cl.govegan.mssearchfood.repositories.reciperepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.lang.NonNull;

import cl.govegan.mssearchfood.models.recipe.Recipe;

@RepositoryRestResource(collectionResourceRel = "recipes", path = "recipes")
public interface RecipeRepository extends MongoRepository<Recipe, String>{

   @Override
   @NonNull
   Page<Recipe> findAll(@NonNull Pageable pageable);

   @Query("{ 'title' : { $regex: ?0, $options: 'i' } }")
   Page<Recipe> findByTitleContaining(@NonNull String keywords, Pageable pageable);

}