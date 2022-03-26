package com.nelanga.cas.user;

import com.nelanga.cas.commons.enums.ErrorTypes;
import com.nelanga.cas.commons.user.AppUserDTO;
import com.nelanga.cas.commons.user.AppUserDetails;
import com.nelanga.cas.exception.types.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AppUserService {

    @Autowired
    private UserRepository userRepository;

    public AppUserDTO authenticatedUser() {
        /*
        * Principal only contains userId for now
        * */
        AppUserDetails currentUser = (AppUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = currentUser.getId();
        if (id == null) {
            log.error("{} : Principal does not contain userId", ErrorTypes.CRITICAL.name());
            throw new UserNotFoundException();
        }
        Optional<AppUser> appUser = userRepository.findById(id);

        if (!appUser.isPresent()) {
            log.error("{} : Authenticated user({}) not in database", ErrorTypes.INCONSISTENT.name(), id);
            throw new UserNotFoundException();
        }

        return GenericUserBuilder.buildUserDTO(appUser.get());
    }
}
