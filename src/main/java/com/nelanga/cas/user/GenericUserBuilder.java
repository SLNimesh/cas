package com.nelanga.cas.user;

import com.nelanga.cas.commons.user.AppUserDTO;
import com.nelanga.cas.commons.user.AppUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public class GenericUserBuilder {

    public static AppUserDetails buildUserDetails(AppUser user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
                .collect(Collectors.toList());
        return new AppUserDetails(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    public static AppUserDTO buildUserDTO(AppUser user) {
        return new AppUserDTO(
                user.getFirstname(),
                user.getLastname(),
                user.getUsername(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getUserType(),
                user.getProfilePic(),
                user.getBannedAccount(),
                user.getCreatedDate(),
                user.getModifiedDate()
        );
    }
}
