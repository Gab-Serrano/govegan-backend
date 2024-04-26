package cl.govegan.mssearchrecipe.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cl.govegan.mssearchrecipe.models.Recipe;

@RepositoryRestResource(collectionResourceRel = "recipes", path = "recipes")
public interface RecipeRepository extends MongoRepository<Recipe, String>{

   List<Recipe> findAll();
   List<Recipe> findByTitleContaining(String keywords);

}