package com.nelanga.cas.user;

import com.nelanga.cas.exception.types.AuthFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        /*
        *  NOTE: Email and password is used for authentication
        * */
        AppUser user = userRepository.findByEmail(email).orElseThrow(AuthFailedException::new);
        return GenericUserBuilder.buildUserDetails(user);
    }
}
