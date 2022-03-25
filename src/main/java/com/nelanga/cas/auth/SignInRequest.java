package com.nelanga.cas.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignInRequest {

    @Email
    @NotBlank
    private String email;
    @Size(min = 8)
    @NotBlank
    private String password;
}
