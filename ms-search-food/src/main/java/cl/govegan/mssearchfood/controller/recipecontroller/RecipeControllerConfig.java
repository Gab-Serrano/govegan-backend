package cl.govegan.mssearchfood.controller.recipecontroller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class RecipeControllerConfig implements WebMvcConfigurer{
   
   @Override
   public void addCorsMappings(CorsRegistry registry) {
       registry.addMapping("/**")
               .allowedOrigins("*")
               .allowedMethods("GET", "POST", "PUT", "DELETE")
               .allowedHeaders("*");
   }
}
