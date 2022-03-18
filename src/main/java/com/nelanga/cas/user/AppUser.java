package com.nelanga.cas.user;

import com.nelanga.cas.commons.enums.SignInMethod;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        schema = "auth",
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"username", "email"})
        }
)
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstname;
    private String lastname;
    @NotBlank
    @Size(max = 16)
    private String username;
    @NotBlank
    @Size(max = 64)
    @Email
    private String email;
    private String password;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private SignInMethod signIn;
    private String profilePic;
    private Boolean bannedAccount;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    /*
    * FetchType.LAZY - data fetched lazily when the getter() is called @ first
    * */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            schema = "auth",
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
