package cl.govegan.msauth.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.govegan.msauth.auth.requests.LoginRequest;
import cl.govegan.msauth.auth.requests.RegisterRequest;
import cl.govegan.msauth.auth.response.AuthResponse;
import cl.govegan.msauth.auth.services.AuthService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
   
   @PostMapping("/loginUser")
   public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
       
       return ResponseEntity.ok(authService.loginUser(loginRequest));
   }
   
   @PostMapping("/registerUser")
   public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
       
       return ResponseEntity.ok(authService.registerUser(registerRequest));

   }

}
