package com.nelanga.cas.user;

import com.nelanga.cas.commons.user.AppUserDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "User Details",
        description = "Resource for getting user details"
)
@RestController
@RequestMapping("api/v1/users")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/authenticated")
    @ResponseStatus(HttpStatus.OK)
    public AppUserDTO authenticatedUser() {
        return appUserService.authenticatedUser();
    }

}
