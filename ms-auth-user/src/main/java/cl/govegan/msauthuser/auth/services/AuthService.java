package cl.govegan.msauthuser.auth.services;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.govegan.msauthuser.auth.models.RefreshToken;
import cl.govegan.msauthuser.auth.requests.LoginRequest;
import cl.govegan.msauthuser.auth.requests.RegisterRequest;
import cl.govegan.msauthuser.auth.response.AuthResponse;
import cl.govegan.msauthuser.exceptions.DuplicateUsernameException;
import cl.govegan.msauthuser.user.config.UserDetailsImpl;
import cl.govegan.msauthuser.user.models.Role;
import cl.govegan.msauthuser.user.models.User;
import cl.govegan.msauthuser.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

      private final UserRepository userRepository;
      private final JwtService jwtService;
      private final RefreshTokenService refreshTokenService;
      private final PasswordEncoder passwordEncoder;
      private final AuthenticationManager authenticationManager;

      public AuthResponse loginUser(LoginRequest loginRequest) {
            authenticationManager.authenticate(loginRequest.toAuthentication());
            User user = userRepository.findByUsername(loginRequest.getUsername()).get();
            String accessToken = jwtService.generateToken(new UserDetailsImpl(user));
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getUsername());
            return AuthResponse.builder()
                        .message("User logged in successfully")
                        .token(accessToken)
                        .refreshToken(refreshToken.getToken())
                        .build();
      }

      public AuthResponse registerUser(RegisterRequest registerRequest) {
        Optional<User> existingUser = userRepository.findByUsername(registerRequest.getUsername());
        if (existingUser.isPresent()) {
            throw new DuplicateUsernameException("Username " + registerRequest.getUsername() + " is already taken.");
        }

        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .role(Role.USER)
                .build();

        userRepository.save(user);
        String accessToken = jwtService.generateToken(new UserDetailsImpl(user));
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getUsername());

        return AuthResponse.builder()
                .message("User registered successfully")
                .token(accessToken)
                .refreshToken(refreshToken.getToken())
                .build();
    }

      public String generateAccessToken(String username) {
            User user = userRepository.findByUsername(username).get();
            return jwtService.generateToken(new UserDetailsImpl(user));
      }

}
