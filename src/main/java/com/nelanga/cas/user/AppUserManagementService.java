package com.nelanga.cas.user;

import com.nelanga.cas.auth.SignUpRequest;
import com.nelanga.cas.commons.enums.RoleType;
import com.nelanga.cas.commons.enums.SignInMethod;
import com.nelanga.cas.commons.user.AppUserDTO;
import com.nelanga.cas.jwt.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AppUserManagementService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private JwtUtil jwtUtil;

    @Autowired
    public AppUserManagementService(PasswordEncoder passwordEncoder,
                                    UserRepository userRepository,
                                    JwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public AppUserDTO signUpAppUser(SignUpRequest signUpRequest) {
        AppUser newUser = new AppUser();
        BeanUtils.copyProperties(signUpRequest, newUser, "password");

        newUser.setUserType(RoleType.ROLE_USER);
        newUser.setSignIn(SignInMethod.SYSTEM);
        newUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        AppUser savedUser = userRepository.save(newUser);

        AppUserDTO newUserDTO = new AppUserDTO();
        BeanUtils.copyProperties(savedUser, newUserDTO);
        return newUserDTO;
    }

}
