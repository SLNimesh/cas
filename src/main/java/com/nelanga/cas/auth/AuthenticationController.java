package com.nelanga.cas.auth;

import com.nelanga.cas.commons.user.AppUserDTO;
import com.nelanga.cas.user.AppUserManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(
        name = "Authentication",
        description = "Resource for signIn/signUp"
)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AppUserManagementService userManagementService;

    @Autowired
    private AuthenticationService authService;


    @GetMapping("/hello")
    public String hello() {
        return "Hello from AuthControl";
    }

    @Operation(summary = "Sign in user")
    @PostMapping("/sign-in")
    private ResponseEntity<Object> signInUser(@RequestBody @Valid SignInRequest request) {
        return authService.signInUser(request);
    }

    @Operation(summary = "Sign up user")
    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    private AppUserDTO registerUser(@RequestBody @Valid SignUpRequest request) {
        return userManagementService.signUpAppUser(request);
    }

    @Operation(summary = "New access token for the refresh token")
    @PostMapping("/token/refresh")
    private ResponseEntity<Object> refreshToken(@RequestBody String refreshToken) {
        return ResponseEntity.ok(refreshToken);
    }
}
