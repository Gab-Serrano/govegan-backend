package cl.govegan.msauthuser.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.govegan.msauthuser.user.models.User;
import cl.govegan.msauthuser.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserRepository userRepository;

   @GetMapping("/profile")
   public ResponseEntity<User> user (@RequestParam String username) {

       User user = userRepository.findByUsername(username)
           .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

       return ResponseEntity.ok().body(user);
   }
}
