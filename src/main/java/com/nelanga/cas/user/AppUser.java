package com.nelanga.cas.user;

import com.nelanga.cas.commons.enums.RoleType;
import com.nelanga.cas.commons.enums.SignInMethod;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
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
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleType userType;
    @Enumerated(EnumType.STRING)
    private SignInMethod signIn;
    private String profilePic;
    private Boolean bannedAccount;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
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
