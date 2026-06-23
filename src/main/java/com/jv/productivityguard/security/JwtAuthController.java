package com.jv.productivityguard.security;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableConfigurationProperties(JwtProperties.class)
public class JwtAuthController {
  private final JwtService jwtService;

  public JwtAuthController(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  @PostMapping("/api/auth/login")
  TokenResponse login(@RequestBody LoginRequest request) {
    return new TokenResponse(jwtService.generateToken(request.email()));
  }

  public record LoginRequest(@NotBlank String email, @NotBlank String password) {}
  public record TokenResponse(String token) {}
}
