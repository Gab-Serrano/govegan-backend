package cl.govegan.mssearchfood.models.food;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "foodCategories")
public class FoodCategory {

   @Id
   private String id;
   private String categoryName;
      
}
