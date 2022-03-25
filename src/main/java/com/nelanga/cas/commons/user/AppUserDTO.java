package com.nelanga.cas.commons.user;

import com.nelanga.cas.commons.enums.RoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AppUserDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String phoneNumber;
    private RoleType userType;
    private String profilePic;
    private Boolean bannedAccount;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

}
