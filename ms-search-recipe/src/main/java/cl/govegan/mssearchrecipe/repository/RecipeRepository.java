package cl.govegan.mssearchrecipe.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import cl.govegan.mssearchrecipe.models.Recipe;

@RepositoryRestResource(collectionResourceRel = "recipes", path = "recipes")
public interface RecipeRepository extends MongoRepository<Recipe, String>{

   Page<Recipe> findAll(Pageable pageable);

   @Query("{ 'title' : { $regex: ?0, $options: 'i' } }")
   List<Recipe> findByTitleContaining(String keywords);

}