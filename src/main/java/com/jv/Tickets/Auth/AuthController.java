package com.jv.Tickets.Auth;

import com.jv.Tickets.Utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//Ruta padre
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody LoginRequest request){
        AuthResponse authResponse = authService.login(request);
        return ResponseEntity.ok(ApiResponse.<AuthResponse>builder()
                        .isSuccess(true)
                        .message("Login successful")
                        .data(authResponse)
                        .build());
    }
    @PostMapping(value = "register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@RequestBody RegisterRequest request){
        AuthResponse authResponse = authService.register(request);
        return ResponseEntity.ok(ApiResponse.<AuthResponse>builder()
                .isSuccess(true)
                .message("Register successful")
                .data(authResponse)
                .build());
    }
}
