package com.gelinski.superquiz.controller;

import com.gelinski.superquiz.dto.security.AccountCredentialsDTO;
import com.gelinski.superquiz.dto.security.SignUpDTO;
import com.gelinski.superquiz.dto.security.TokenDTO;
import com.gelinski.superquiz.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody AccountCredentialsDTO accountCredentialsDTO) {
        return ResponseEntity.ok(authService.login(accountCredentialsDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody SignUpDTO signUpDTO) {
        authService.register(signUpDTO);
        return ResponseEntity.ok("User registered successfully");
    }
}
