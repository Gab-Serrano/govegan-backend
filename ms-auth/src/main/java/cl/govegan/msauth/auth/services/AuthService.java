package cl.govegan.msauth.auth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.govegan.msauth.auth.requests.LoginRequest;
import cl.govegan.msauth.auth.requests.RegisterRequest;
import cl.govegan.msauth.auth.response.AuthResponse;
import cl.govegan.msauth.user.config.UserDetailsImpl;
import cl.govegan.msauth.user.models.Role;
import cl.govegan.msauth.user.models.User;
import cl.govegan.msauth.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

   private final UserRepository userRepository;
   private final JwtService jwtService;
   private final PasswordEncoder passwordEncoder;
   private final AuthenticationManager authenticationManager;
   private final Logger logger = LoggerFactory.getLogger(AuthService.class);

   public AuthResponse loginUser(LoginRequest loginRequest) {
      authenticationManager.authenticate(loginRequest.toAuthentication());
      User user = userRepository.findByUsername(loginRequest.getUsername()).get();
      return AuthResponse.builder()
            .message("User logged in successfully")
            .token(jwtService.generateToken(new UserDetailsImpl(user)))
            .build();
   }

   public AuthResponse registerUser(RegisterRequest registerRequest) {

      User user = User.builder()
            .username(registerRequest.getUsername())
            .password(passwordEncoder.encode(registerRequest.getPassword()))
            .email(registerRequest.getEmail())
            .role(Role.USER)
            .build();

      userRepository.save(user);

      return AuthResponse.builder()
            .message("User registered successfully")
            .token(jwtService.generateToken(new UserDetailsImpl(user)))
            .build();
   }

}
