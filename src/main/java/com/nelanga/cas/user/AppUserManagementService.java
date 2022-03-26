package com.nelanga.cas.user;

import com.nelanga.cas.auth.SignUpRequest;
import com.nelanga.cas.commons.enums.RoleType;
import com.nelanga.cas.commons.enums.SignInMethod;
import com.nelanga.cas.commons.user.AppUserDTO;
import com.nelanga.cas.exception.types.EmailInUseException;
import com.nelanga.cas.exception.types.UsernameTakenException;
import com.nelanga.cas.utility.CreateJwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AppUserManagementService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private CreateJwtUtil jwtUtil;

    @Autowired
    public AppUserManagementService(PasswordEncoder passwordEncoder,
                                    UserRepository userRepository,
                                    CreateJwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public AppUser createAppUser(SignUpRequest signUpRequest) {
        AppUser newUser = new AppUser();
        BeanUtils.copyProperties(signUpRequest, newUser, "password");

        newUser.setUserType(RoleType.ROLE_USER);
        newUser.setSignIn(SignInMethod.SYSTEM);
        newUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        return userRepository.save(newUser);
    }

    @Transactional
    public AppUserDTO signUpAppUser(SignUpRequest signUpRequest) {

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new EmailInUseException();
        }
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new UsernameTakenException();
        }

        AppUser savedUser = createAppUser(signUpRequest);

        return GenericUserBuilder.buildUserDTO(savedUser);
    }

}
