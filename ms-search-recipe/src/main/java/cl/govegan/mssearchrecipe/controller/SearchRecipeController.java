package cl.govegan.mssearchrecipe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.govegan.mssearchrecipe.models.Recipe;
import cl.govegan.mssearchrecipe.service.RecipeService;
import cl.govegan.mssearchrecipe.utils.HttpResponse;

@RestController
@RequestMapping("/api/v1")
public class SearchRecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/status")
    public ResponseEntity<HttpResponse> checkApiStatus() {
        return ResponseEntity.status(HttpStatus.OK).body(new HttpResponse(200, "API is running fine"));
    }

    @GetMapping("/recipes")
    public ResponseEntity<HttpResponse> findAllRecipes(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            
            Pageable pageable = PageRequest.of(page, size);
            Page<Recipe> recipesResult = recipeService.findAll(pageable);

            if (recipesResult.hasContent()) {
                return ResponseEntity.status(HttpStatus.OK).body(new HttpResponse(200, recipesResult));
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new HttpResponse(404, "No recipes found"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HttpResponse(500, e.getMessage()));
        }
    }

    @GetMapping("/recipes/findBySearch")
    public ResponseEntity<HttpResponse> searchRecipeByText(@RequestParam String search) {
        try {
            Optional<List<Recipe>> recipesResult = Optional.ofNullable(recipeService.findByTitleContaining(search));
            if (recipesResult.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(new HttpResponse(200, recipesResult.get()));
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new HttpResponse(404, "No recipes found"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new HttpResponse(500, e.getMessage()));
        }
    }

}
