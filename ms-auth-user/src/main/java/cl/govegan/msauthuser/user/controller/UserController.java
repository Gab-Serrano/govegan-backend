package cl.govegan.msauthuser.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

   @PostMapping("/profile")
   public ResponseEntity<User> user() {
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

       if (authentication == null || !authentication.isAuthenticated()) {
           throw new RuntimeException("No autenticado");
       }

       String username = authentication.getName();
       User user = userRepository.findByUsername(username)
           .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

       return ResponseEntity.ok().body(user);
   }
}
