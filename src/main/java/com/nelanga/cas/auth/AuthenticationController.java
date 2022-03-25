package com.nelanga.cas.auth;

import com.nelanga.cas.commons.user.AppUserDTO;
import com.nelanga.cas.user.AppUserManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/auth")
@Tag(
        name = "Authentication",
        description = "Resource for signIn/signUp"
)
public class AuthenticationController {

    @Autowired
    private AppUserManagementService userManagementService;

    // TODO: Return sign in and sign in up with SET COOKIE HEADER

    @GetMapping("/hello")
    public String hello() {
        return "Hello from AuthControl";
    }

    @Operation(summary = "Sign in user")
    @PostMapping("/sign-in")
    private ResponseEntity<Object> signInUser(@RequestBody @Valid SignInRequest request) {

        return ResponseEntity.ok(request);
    }

    @Operation(summary = "Sign up user")
    @PostMapping("/sign-up")
    private ResponseEntity<Object> registerUser(@RequestBody @Valid SignUpRequest request) {
        AppUserDTO userDTO = userManagementService.signUpAppUser(request);
        return ResponseEntity.ok(request);
    }

    @Operation(summary = "New access token for the refresh token")
    @PostMapping("/token/refresh")
    private ResponseEntity<Object> refreshToken(@RequestBody String refreshToken) {

        return ResponseEntity.ok(refreshToken);
    }
}
