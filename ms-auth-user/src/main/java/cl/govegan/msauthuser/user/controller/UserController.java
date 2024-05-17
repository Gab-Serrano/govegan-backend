package cl.govegan.msauthuser.user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.govegan.msauthuser.user.models.Gender;
import cl.govegan.msauthuser.user.models.Profile;
import cl.govegan.msauthuser.user.models.User;
import cl.govegan.msauthuser.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/profile")
    public ResponseEntity<User> user(@RequestParam String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return ResponseEntity.ok().body(user);
    }

    @PatchMapping("/profile")
    public ResponseEntity<User> updateUserProfile(@RequestParam String username, @RequestBody Profile updatedProfile) {
        User userToUpdate = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        userToUpdate.setProfile(updatedProfile);
        userRepository.save(userToUpdate);

        return ResponseEntity.ok().body(userToUpdate);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<Void> deleteUser(@RequestParam String username) {
        User userToDelete = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        userRepository.delete(userToDelete);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok().body(users);
    }

    @PatchMapping("/password")
    public ResponseEntity<User> updateUserPassword(@RequestParam String username, @RequestBody Map<String, String> newPasswordRequest) {
        User userToUpdate = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        
        userToUpdate.setPassword(passwordEncoder.encode(newPasswordRequest.get("newPassword")));
        userRepository.save(userToUpdate);

        return ResponseEntity.ok().body(userToUpdate);
    }

    @PatchMapping("/profile/partial")
    public ResponseEntity<User> partiallyUpdateUserProfile(@RequestParam String username, @RequestBody Map<String, Object> updates) {
        User userToUpdate = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        updates.forEach((key, value) -> {
            switch (key) {
                case "name" -> userToUpdate.getProfile().setName((String) value);
                case "profilePicture" -> userToUpdate.getProfile().setProfilePicture((String) value);
                case "age" -> userToUpdate.getProfile().setAge((Integer) value);
                case "gender" -> userToUpdate.getProfile().setGender(Gender.valueOf(((String) value).toUpperCase()));
                case "weight" -> userToUpdate.getProfile().setWeight((Double) value);
                case "height" -> userToUpdate.getProfile().setHeight((Double) value);
                case "city" -> userToUpdate.getProfile().setCity((String) value);
                case "country" -> userToUpdate.getProfile().setCountry((String) value);
                case "allergies" -> userToUpdate.getProfile().setAllergies((String[]) value);
                case "favoriteFoods" -> userToUpdate.getProfile().setFavoriteFoods((String[]) value);
                case "unwantedFoods" -> userToUpdate.getProfile().setUnwantedFoods((String[]) value);
                case "favoriteRecipes" -> userToUpdate.getProfile().setFavoriteRecipes((String[]) value);
                case "caloriesPerDay" -> userToUpdate.getProfile().setCaloriesPerDay((Integer) value);
                case "waterPerDay" -> userToUpdate.getProfile().setWaterPerDay((Double) value);
                case "title" -> userToUpdate.getProfile().setTitle((String) value);
                default -> throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        userRepository.save(userToUpdate);
        return ResponseEntity.ok().body(userToUpdate);
    }

    @PatchMapping("/email")
    public ResponseEntity<User> updateUserEmail(@RequestParam String username, @RequestBody Map<String, String> newEmailRequest) {
        User userToUpdate = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        
        userToUpdate.setEmail(newEmailRequest.get("newEmail"));
        userRepository.save(userToUpdate);

        return ResponseEntity.ok().body(userToUpdate);
    }
}
