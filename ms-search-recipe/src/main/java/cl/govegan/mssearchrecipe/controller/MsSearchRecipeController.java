package cl.govegan.mssearchrecipe.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class MsSearchRecipeController {

   private static final String template = "Hello world";
   
   @GetMapping("/")
   public String checkApiStatus() {
       return template;
   }
   
   
}
