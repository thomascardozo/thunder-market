package com.tm.authapi.controller;

import com.tm.authapi.dto.AuthRequest;
import com.tm.authapi.dto.TokenDTO;
import com.tm.authapi.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/auth")
public class AuthControler {

    private AuthService authService;

    @PostMapping("login")
    public TokenDTO login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }

    @PostMapping("token/validate")
    public TokenDTO tokenValidate(@RequestHeader String accessToken) {
        return authService.validateToken(accessToken);
    }

}
