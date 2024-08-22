package uz.pdp.gymproject.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.gymproject.dto.LoginDto;
import uz.pdp.gymproject.dto.RegisterDto;
import uz.pdp.gymproject.response.Response;
import uz.pdp.gymproject.security.JwtUtil;
import uz.pdp.gymproject.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuthService authService;

    @Tag(name = "Register user")
    @Transactional
    @PostMapping("/register")
    public HttpEntity<?> register(@RequestBody RegisterDto registerDto) {
        String email=authService.register(registerDto);
        return ResponseEntity.ok(
                Response.builder().message("Token").data("Bearer " + jwtUtil.generateToken(email)).build()
        );
    }

    @Tag(name = "Login user")
    @Transactional
    @SneakyThrows
    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto) {
        String email=authService.login(loginDto);
        return ResponseEntity.ok(
                Response.builder().message("Token").data("Bearer " + jwtUtil.generateToken(email)).build()
        );
    }
}
