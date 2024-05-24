package cl.govegan.msauthuser.auth.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import cl.govegan.msauthuser.auth.jwt.JwtService;
import cl.govegan.msauthuser.auth.models.RefreshToken;
import cl.govegan.msauthuser.auth.requests.ForgotPasswordRequest;
import cl.govegan.msauthuser.auth.requests.LoginRequest;
import cl.govegan.msauthuser.auth.requests.RegisterRequest;
import cl.govegan.msauthuser.auth.response.AuthResponse;
import cl.govegan.msauthuser.auth.services.mailservice.EmailRequest;
import cl.govegan.msauthuser.auth.services.mailservice.EmailService;
import cl.govegan.msauthuser.exceptions.DuplicateUsernameException;
import cl.govegan.msauthuser.user.models.User;
import cl.govegan.msauthuser.user.repositories.UserRepository;
import cl.govegan.msauthuser.user.services.UserConverter;
import cl.govegan.msauthuser.user.services.UserDetailsImpl;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

      @Value("${spring.url.resetpasword}")
      private String resetPasswordUrl;

      private final UserRepository userRepository;
      private final JwtService jwtService;
      private final RefreshTokenService refreshTokenService;
      private final PasswordEncoder passwordEncoder;
      private final AuthenticationManager authenticationManager;
      private final UserConverter userConverter;
      private final EmailService emailService;

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

        User user = userConverter.toUser(registerRequest, passwordEncoder);
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

      public AuthResponse forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
            Optional<User> user = userRepository.findByEmail(forgotPasswordRequest.getEmail());
    
            if (user.isPresent()) {
                String token = jwtService.generateToken(new UserDetailsImpl(user.get()));
    
                EmailRequest emailRequest = EmailRequest.builder()
                        .to(user.get().getEmail())
                        .subject("GoVegan: Recuperar contraseña")
                        .name(user.get().getProfile().getName())
                        .body("Para recuperar tu contraseña, utiliza el siguiente link:\n" + resetPasswordUrl + "/auth/reset-password?token=" + token)
                        .build();
    
                Context context = new Context();
                context.setVariable("name", emailRequest.getName());
                context.setVariable("body", emailRequest.getBody());
                context.setVariable("subject", emailRequest.getSubject());
    
                try {
                    emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), "emailTemplate", context);
    
                    return AuthResponse.builder()
                            .message("Email sent successfully")
                            .build();
    
                } catch (MessagingException e) {
                    return AuthResponse.builder()
                            .message("Error sending email")
                            .build();
                }
    
            } else {
                return AuthResponse.builder()
                        .message("Email not found")
                        .build();
            }
        }

}
