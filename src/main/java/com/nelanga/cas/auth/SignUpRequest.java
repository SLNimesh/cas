package com.nelanga.cas.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequest {
    @NotBlank
    @Size(max = 16)
    private String firstname;
    @NotBlank
    @Size(max = 16)
    private String lastname;
    @NotBlank
    @Size(max = 64)
    @Email
    private String email;
    @NotBlank
    @Size(max = 16)
    private String username;
    @NotBlank
    @Size(min = 8)
    private String password;
}
