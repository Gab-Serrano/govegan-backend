package cl.govegan.mssearchfood.controller.foodcontroller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.govegan.mssearchfood.exceptions.ResourceNotFoundException;
import cl.govegan.mssearchfood.models.food.Food;
import cl.govegan.mssearchfood.services.foodservices.FoodService;
import cl.govegan.mssearchfood.utils.requests.food.FoodRequest;
import cl.govegan.mssearchfood.utils.responses.ResponseHttp;

@RestController
@RequestMapping("/api/v1/foods")
public class FoodController {

   @Autowired
   private FoodService foodService;

   @GetMapping()
   public ResponseEntity<ResponseHttp<Page<Food>>> findAllFoods(
         @RequestParam(value = "page", defaultValue = "0") int page,
         @RequestParam(value = "size", defaultValue = "10") int size) {
      Pageable pageable = PageRequest.of(page, size);
      Page<Food> foodsResult = foodService.findAll(pageable);

      if (foodsResult.hasContent()) {
         return ResponseEntity.status(HttpStatus.OK).body(new ResponseHttp<>(200, "Foods retrived", foodsResult));
      } else {
         throw new ResourceNotFoundException("No foods found");
      }
   }

   @GetMapping("/findBySearch")
   public ResponseEntity<ResponseHttp<Page<Food>>> searchFoodByText(
         @RequestParam String search,
         @RequestParam(value = "page", defaultValue = "0") int page,
         @RequestParam(value = "size", defaultValue = "10") int size) {
      Pageable pageable = PageRequest.of(page, size);
      Page<Food> foodsResult = foodService.findByFoodNameContaining(search, pageable);
      if (!foodsResult.isEmpty()) {
         return ResponseEntity.status(HttpStatus.OK).body(new ResponseHttp<>(200, "Foods retrived", foodsResult));
      } else {
         throw new ResourceNotFoundException("No foods found");
      }
   }

   @GetMapping("/findById")
   public ResponseEntity<ResponseHttp<Food>> findFoodById(@RequestParam String id) {
      Optional<Food> food = foodService.findById(id);
      if (food.isPresent()) {
         return ResponseEntity.status(HttpStatus.OK).body(new ResponseHttp<>(200, "Food by ID retrived", food.get()));
      } else {
         throw new ResourceNotFoundException("Food not found");
      }
   }

   @PostMapping()
   public ResponseEntity<ResponseHttp<Food>> saveFood(@RequestBody FoodRequest foodRequest) {

      Food savedFood = foodService.saveFood(foodRequest);
      return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseHttp<>(201, "Food saved", savedFood));
   }

   @DeleteMapping()
   public ResponseEntity<ResponseHttp<String>> deleteFood(@RequestParam String id) {
      foodService.deleteById(id);
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseHttp<>(200, "Food deleted successfully", null));
   }

   @GetMapping("/categories")
   public ResponseEntity<ResponseHttp<?>> findAllCategories() {
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseHttp<>(200, "Categories retrived", foodService.findAllCategories()));
   }

}