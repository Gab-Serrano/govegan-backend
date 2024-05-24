package cl.govegan.msauthuser.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.govegan.msauthuser.auth.models.RefreshToken;
import cl.govegan.msauthuser.auth.requests.ForgotPasswordRequest;
import cl.govegan.msauthuser.auth.requests.LoginRequest;
import cl.govegan.msauthuser.auth.requests.RegisterRequest;
import cl.govegan.msauthuser.auth.requests.TokenRefreshRequest;
import cl.govegan.msauthuser.auth.response.AuthResponse;
import cl.govegan.msauthuser.auth.response.TokenRefreshResponse;
import cl.govegan.msauthuser.auth.services.AuthService;
import cl.govegan.msauthuser.auth.services.RefreshTokenService;
import cl.govegan.msauthuser.exceptions.TokenRefreshException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/loginUser")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.loginUser(loginRequest));
    }

    @PostMapping("/registerUser")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.registerUser(registerRequest));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<TokenRefreshResponse> refreshToken(@RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUsername)
                .map(username -> {
                    String newAccessToken = authService.generateAccessToken(username);
                    return ResponseEntity.ok(new TokenRefreshResponse(newAccessToken, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken, "Refresh token is not in database!"));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<AuthResponse> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        return ResponseEntity.ok(authService.forgotPassword(forgotPasswordRequest));
    }
    
}
