package cl.govegan.msauthuser.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.govegan.msauthuser.user.models.Profile;
import cl.govegan.msauthuser.user.models.User;
import cl.govegan.msauthuser.user.repositories.UserRepository;
import cl.govegan.msauthuser.user.services.ResponseHttpService;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author gabse
 */
@EnableMethodSecurity(prePostEnabled = true)
@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        @PreAuthorize("hasRole('ADMIN')")
        @GetMapping("/role")
        public Map<String, String> getRole(@RequestParam("username") String username) {
                User user = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
                Map<String, String> response = new HashMap<>();
                response.put("role", user.getRoles().toString());
                return response;
        }

        @GetMapping("/profile")
        public ResponseEntity<User> user(Authentication authentication) {

                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                String username = userDetails.getUsername();

                User user = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

                return ResponseEntity.ok().body(user);
        }

        @PatchMapping("/profile")
        public ResponseEntity<User> updateUserProfile(Authentication authentication,
                        @RequestBody Profile updatedProfile) {

                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                String username = userDetails.getUsername();

                User userToUpdate = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

                userToUpdate.setProfile(updatedProfile);
                userRepository.save(userToUpdate);

                return ResponseEntity.ok().body(userToUpdate);
        }

        @DeleteMapping("/deleteUser")
        public ResponseEntity<Map<String, String>> deleteUser(Authentication authentication) {

                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                String username = userDetails.getUsername();

                User userToDelete = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
                userRepository.delete(userToDelete);
                return ResponseEntity.ok().body(Map.of("message", "User " + username + " deleted succesfully."));
        }

        @PreAuthorize("hasRole('ADMIN')")
        @DeleteMapping("/deleteUserByUsername")
        public ResponseEntity<String> deleteUserByUsername(@RequestParam String username) {
                User userToDelete = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
                userRepository.delete(userToDelete);
                return ResponseEntity.ok().body("User " + username + " deleted succesfully.");
        }

        @PreAuthorize("hasRole('ADMIN')")
        @GetMapping("/all")
        public ResponseEntity<List<User>> getAllUsers() {
                List<User> users = userRepository.findAll();
                return ResponseEntity.ok().body(users);
        }

        @PatchMapping("/password")
        public ResponseEntity<User> updateUserPassword(Authentication authentication,
                        @RequestBody Map<String, String> newPasswordRequest) {

                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                String username = userDetails.getUsername();

                User userToUpdate = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

                userToUpdate.setPassword(passwordEncoder.encode(newPasswordRequest.get("newPassword")));
                userRepository.save(userToUpdate);

                return ResponseEntity.ok().body(userToUpdate);
        }

        @PreAuthorize("hasRole('ADMIN')")
        @PatchMapping("/passwordByUsername")
        public ResponseEntity<User> updateUserPasswordByUsername(@RequestParam String username,
                        @RequestBody Map<String, String> newPasswordRequest) {

                User userToUpdate = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

                userToUpdate.setPassword(passwordEncoder.encode(newPasswordRequest.get("newPassword")));
                userRepository.save(userToUpdate);

                return ResponseEntity.ok().body(userToUpdate);
        }

        @PatchMapping("/email")
        public ResponseEntity<User> updateUserEmail(Authentication authentication,
                        @RequestBody Map<String, String> newEmailRequest) {

                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                String username = userDetails.getUsername();

                User userToUpdate = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

                userToUpdate.setEmail(newEmailRequest.get("newEmail"));
                userRepository.save(userToUpdate);

                return ResponseEntity.ok().body(userToUpdate);
        }

        @PreAuthorize("hasRole('ADMIN')")
        @PatchMapping("/emailByUsername")
        public ResponseEntity<User> updateUserEmailByUsername(@RequestParam String username,
                        @RequestBody Map<String, String> newEmailRequest) {

                User userToUpdate = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

                userToUpdate.setEmail(newEmailRequest.get("newEmail"));
                userRepository.save(userToUpdate);

                return ResponseEntity.ok().body(userToUpdate);
        }

        @PostMapping("/addFavoriteRecipeById")
        public ResponseEntity<ResponseHttpService> addFavoriteRecipeById(Authentication authentication,
                        @RequestParam String recipeId) {

                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                String username = userDetails.getUsername();

                User userToUpdate = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

                if (userToUpdate.getProfile().getFavoriteRecipes().contains(recipeId)) {

                        ResponseHttpService response = ResponseHttpService.builder()
                                        .status(HttpStatus.BAD_REQUEST.value())
                                        .message("Recipe already in favorites.")
                                        .data(userToUpdate.getProfile().getFavoriteRecipes())
                                        .build();
                        return ResponseEntity.badRequest().body(response);
                }

                userToUpdate.getProfile().getFavoriteRecipes().add(recipeId);
                userRepository.save(userToUpdate);

                ResponseHttpService response = ResponseHttpService.builder()
                                .status(HttpStatus.OK.value())
                                .message("Favorite recipe added successfully.")
                                .data(userToUpdate.getProfile().getFavoriteRecipes())
                                .build();

                return ResponseEntity.ok().body(response);
        }

        @GetMapping("/getFavoriteRecipes")
        public ResponseEntity<ResponseHttpService> getFavoriteRecipes(
                        Authentication authentication,
                        @RequestParam(value = "page", defaultValue = "0") int page,
                        @RequestParam(value = "size", defaultValue = "10") int size) {

                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                String username = userDetails.getUsername();

                User user = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

                List<String> favoriteRecipes = user.getProfile().getFavoriteRecipes();

                // Crear un objeto Pageable para la paginación
                Pageable pageable = PageRequest.of(page, size);

                // Crear un objeto Page a partir de la lista de recetas favoritas
                Page<String> favoriteRecipesPage = new PageImpl<>(favoriteRecipes, pageable, favoriteRecipes.size());

                return ResponseEntity.ok().body(ResponseHttpService.builder()
                                .status(HttpStatus.OK.value())
                                .message("Favorite recipes retrieved successfully.")
                                .data(favoriteRecipesPage)
                                .build());
        }

        @DeleteMapping("/deleteFavoriteRecipeById")
        public ResponseEntity<ResponseHttpService> deleteFavoriteRecipeById(Authentication authentication,
                        @RequestParam String recipeId) {

                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                String username = userDetails.getUsername();

                User userToUpdate = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

                if (!userToUpdate.getProfile().getFavoriteRecipes().contains(recipeId)) {

                        ResponseHttpService response = ResponseHttpService.builder()
                                        .status(HttpStatus.BAD_REQUEST.value())
                                        .message("Recipe not in favorites.")
                                        .data(userToUpdate.getProfile().getFavoriteRecipes())
                                        .build();
                        return ResponseEntity.badRequest().body(response);
                }

                userToUpdate.getProfile().getFavoriteRecipes().remove(recipeId);
                userRepository.save(userToUpdate);

                ResponseHttpService response = ResponseHttpService.builder()
                                .status(HttpStatus.OK.value())
                                .message("Favorite recipe " + recipeId + " deleted successfully.")
                                .data(userToUpdate.getProfile().getFavoriteRecipes())
                                .build();

                return ResponseEntity.ok().body(response);
        }

        @GetMapping("/isFavoriteRecipe")
        public ResponseEntity<ResponseHttpService> isFavoriteRecipe(Authentication authentication,
                        @RequestParam String recipeId) {

                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                String username = userDetails.getUsername();

                User user = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

                ResponseHttpService response = ResponseHttpService.builder()
                                .status(HttpStatus.OK.value())
                                .message("Recipe is favorite.")
                                .data(user.getProfile().getFavoriteRecipes().contains(recipeId))
                                .build();

                return ResponseEntity.ok().body(response);
        }

        @PostMapping("/addFavoriteFoodById")
        public ResponseEntity<ResponseHttpService> addFavoriteFoodById(Authentication authentication,
                        @RequestParam String foodId) {

                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                String username = userDetails.getUsername();

                User userToUpdate = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

                if (userToUpdate.getProfile().getFavoriteFoods().contains(foodId)) {

                        ResponseHttpService response = ResponseHttpService.builder()
                                        .status(HttpStatus.BAD_REQUEST.value())
                                        .message("Food already in favorites.")
                                        .data(userToUpdate.getProfile().getFavoriteFoods())
                                        .build();
                        return ResponseEntity.badRequest().body(response);
                } else if (userToUpdate.getProfile().getAllergies().contains(foodId)) {
                        ResponseHttpService response = ResponseHttpService.builder()
                                        .status(HttpStatus.BAD_REQUEST.value())
                                        .message("Food already in allergies.")
                                        .data(userToUpdate.getProfile().getAllergies())
                                        .build();
                        return ResponseEntity.badRequest().body(response);
                } else if (userToUpdate.getProfile().getUnwantedFoods().contains(foodId)) {
                        ResponseHttpService response = ResponseHttpService.builder()
                                        .status(HttpStatus.BAD_REQUEST.value())
                                        .message("Food already in unwantedFoods.")
                                        .data(userToUpdate.getProfile().getUnwantedFoods())
                                        .build();
                        return ResponseEntity.badRequest().body(response);
                }

                userToUpdate.getProfile().getFavoriteFoods().add(foodId);
                userRepository.save(userToUpdate);

                ResponseHttpService response = ResponseHttpService.builder()
                                .status(HttpStatus.OK.value())
                                .message("Favorite food added successfully.")
                                .data(userToUpdate.getProfile().getFavoriteFoods())
                                .build();

                return ResponseEntity.ok().body(response);
        }

        @GetMapping("/getFavoriteFoods")
        public ResponseEntity<ResponseHttpService> getFavoriteFoods(Authentication authentication,
                        @RequestParam(value = "page", defaultValue = "0") int page,
                        @RequestParam(value = "size", defaultValue = "10") int size) {

                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                String username = userDetails.getUsername();

                User user = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

                List<String> favoriteFoods = user.getProfile().getFavoriteFoods();

                Pageable pageable = PageRequest.of(page, size);

                Page<String> favoriteFoodsPage = new PageImpl<>(favoriteFoods, pageable, favoriteFoods.size());

                return ResponseEntity.ok().body(ResponseHttpService.builder()
                                .status(HttpStatus.OK.value())
                                .message("Favorite foods retrieved successfully.")
                                .data(favoriteFoodsPage)
                                .build());
        }

        @DeleteMapping("/deleteFavoriteFoodById")
        public ResponseEntity<ResponseHttpService> deleteFavoriteFoodById(Authentication authentication,
                        @RequestParam String foodId) {

                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                String username = userDetails.getUsername();

                User userToUpdate = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

                if (!userToUpdate.getProfile().getFavoriteFoods().contains(foodId)) {

                        ResponseHttpService response = ResponseHttpService.builder()
                                        .status(HttpStatus.BAD_REQUEST.value())
                                        .message("Food not in favorites.")
                                        .data(userToUpdate.getProfile().getFavoriteFoods())
                                        .build();
                        return ResponseEntity.badRequest().body(response);
                }

                userToUpdate.getProfile().getFavoriteFoods().remove(foodId);
                userRepository.save(userToUpdate);

                ResponseHttpService response = ResponseHttpService.builder()
                                .status(HttpStatus.OK.value())
                                .message("Favorite food " + foodId + " deleted successfully.")
                                .data(userToUpdate.getProfile().getFavoriteFoods())
                                .build();

                return ResponseEntity.ok().body(response);
        }

        @GetMapping("/isFavoriteFood")
        public ResponseEntity<ResponseHttpService> isFavoriteFood(Authentication authentication,
                        @RequestParam String foodId) {

                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                String username = userDetails.getUsername();

                User user = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

                ResponseHttpService response = ResponseHttpService.builder()
                                .status(HttpStatus.OK.value())
                                .message("Food is favorite.")
                                .data(user.getProfile().getFavoriteFoods().contains(foodId))
                                .build();

                return ResponseEntity.ok().body(response);
        }

        @PostMapping("/addFoodAlergies")
        public ResponseEntity<ResponseHttpService> addFoodAlergies(Authentication authentication,
                        @RequestParam String foodId) {

                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                String username = userDetails.getUsername();

                User userToUpdate = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

                if (userToUpdate.getProfile().getAllergies().contains(foodId)) {

                        ResponseHttpService response = ResponseHttpService.builder()
                                        .status(HttpStatus.BAD_REQUEST.value())
                                        .message("Food already in allergies.")
                                        .data(userToUpdate.getProfile().getFavoriteFoods())
                                        .build();
                        return ResponseEntity.badRequest().body(response);
                } else if (userToUpdate.getProfile().getFavoriteFoods().contains(foodId)) {
                        ResponseHttpService response = ResponseHttpService.builder()
                                        .status(HttpStatus.BAD_REQUEST.value())
                                        .message("Food already in favorites.")
                                        .data(userToUpdate.getProfile().getAllergies())
                                        .build();
                        return ResponseEntity.badRequest().body(response);
                } else if (userToUpdate.getProfile().getUnwantedFoods().contains(foodId)) {
                        ResponseHttpService response = ResponseHttpService.builder()
                                        .status(HttpStatus.BAD_REQUEST.value())
                                        .message("Food already in unwantedFoods.")
                                        .data(userToUpdate.getProfile().getUnwantedFoods())
                                        .build();
                        return ResponseEntity.badRequest().body(response);
                }

                userToUpdate.getProfile().getAllergies().add(foodId);
                userRepository.save(userToUpdate);

                ResponseHttpService response = ResponseHttpService.builder()
                                .status(HttpStatus.OK.value())
                                .message("Food alergies added successfully.")
                                .data(userToUpdate.getProfile().getAllergies())
                                .build();

                return ResponseEntity.ok().body(response);
        }

        @GetMapping("/getAllAlergies")
        public ResponseEntity<ResponseHttpService> getAllAlergies(Authentication authentication) {

                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                String username = userDetails.getUsername();

                User user = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

                return ResponseEntity.ok().body(ResponseHttpService.builder()
                                .status(HttpStatus.OK.value())
                                .message("Allergies retrieved successfully.")
                                .data(user.getProfile().getAllergies())
                                .build());
        }

        @DeleteMapping("/deleteFoodAlergies")
        public ResponseEntity<ResponseHttpService> deleteFoodAlergies(Authentication authentication,
                        @RequestParam String foodId) {

                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                String username = userDetails.getUsername();

                User userToUpdate = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

                if (!userToUpdate.getProfile().getAllergies().contains(foodId)) {

                        ResponseHttpService response = ResponseHttpService.builder()
                                        .status(HttpStatus.BAD_REQUEST.value())
                                        .message("Food not in allergies.")
                                        .data(userToUpdate.getProfile().getAllergies())
                                        .build();
                        return ResponseEntity.badRequest().body(response);
                }

                userToUpdate.getProfile().getAllergies().remove(foodId);
                userRepository.save(userToUpdate);

                ResponseHttpService response = ResponseHttpService.builder()
                                .status(HttpStatus.OK.value())
                                .message("Food alergies " + foodId + " deleted successfully.")
                                .data(userToUpdate.getProfile().getAllergies())
                                .build();

                return ResponseEntity.ok().body(response);
        }

        @PostMapping("/addUnwantedFood")
        public ResponseEntity<ResponseHttpService> addUnwantedFood(Authentication authentication,
                        @RequestParam String foodId) {

                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                String username = userDetails.getUsername();

                User userToUpdate = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

                if (userToUpdate.getProfile().getUnwantedFoods().contains(foodId)) {

                        ResponseHttpService response = ResponseHttpService.builder()
                                        .status(HttpStatus.BAD_REQUEST.value())
                                        .message("Food already in UnwantedFoods.")
                                        .data(userToUpdate.getProfile().getFavoriteFoods())
                                        .build();
                        return ResponseEntity.badRequest().body(response);
                } else if (userToUpdate.getProfile().getAllergies().contains(foodId)) {
                        ResponseHttpService response = ResponseHttpService.builder()
                                        .status(HttpStatus.BAD_REQUEST.value())
                                        .message("Food already in allergies.")
                                        .data(userToUpdate.getProfile().getAllergies())
                                        .build();
                        return ResponseEntity.badRequest().body(response);
                } else if (userToUpdate.getProfile().getFavoriteFoods().contains(foodId)) {
                        ResponseHttpService response = ResponseHttpService.builder()
                                        .status(HttpStatus.BAD_REQUEST.value())
                                        .message("Food already in FavoriteFoods.")
                                        .data(userToUpdate.getProfile().getUnwantedFoods())
                                        .build();
                        return ResponseEntity.badRequest().body(response);
                }

                userToUpdate.getProfile().getUnwantedFoods().add(foodId);
                userRepository.save(userToUpdate);

                ResponseHttpService response = ResponseHttpService.builder()
                                .status(HttpStatus.OK.value())
                                .message("Food alergies added successfully.")
                                .data(userToUpdate.getProfile().getAllergies())
                                .build();

                return ResponseEntity.ok().body(response);
        }

        @GetMapping("/getAllUnwantedFood")
        public ResponseEntity<ResponseHttpService> getAllUnwantedFood(Authentication authentication) {

                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                String username = userDetails.getUsername();

                User user = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

                return ResponseEntity.ok().body(ResponseHttpService.builder()
                                .status(HttpStatus.OK.value())
                                .message("Allergies retrieved successfully.")
                                .data(user.getProfile().getUnwantedFoods())
                                .build());
        }

        @DeleteMapping("/deleteUnwantedFoods")
        public ResponseEntity<ResponseHttpService> deleteUnwantedFoods(Authentication authentication,
                        @RequestParam String foodId) {

                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                String username = userDetails.getUsername();

                User userToUpdate = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

                if (!userToUpdate.getProfile().getUnwantedFoods().contains(foodId)) {

                        ResponseHttpService response = ResponseHttpService.builder()
                                        .status(HttpStatus.BAD_REQUEST.value())
                                        .message("Food not in allergies.")
                                        .data(userToUpdate.getProfile().getUnwantedFoods())
                                        .build();
                        return ResponseEntity.badRequest().body(response);
                }

                userToUpdate.getProfile().getUnwantedFoods().remove(foodId);
                userRepository.save(userToUpdate);

                ResponseHttpService response = ResponseHttpService.builder()
                                .status(HttpStatus.OK.value())
                                .message("Food alergies " + foodId + " deleted successfully.")
                                .data(userToUpdate.getProfile().getUnwantedFoods())
                                .build();

                return ResponseEntity.ok().body(response);
        }

}
