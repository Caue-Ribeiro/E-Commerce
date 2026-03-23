package com.caue.democommerce.services;

import com.caue.democommerce.entities.User;
import com.caue.democommerce.exceptions.ForbiddenException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public void validateUserAccessToOrder(Long userId){
        User userLogged = userService.authenticated();

        if (!userLogged.hasRole("ROLE_ADMIN") && !orderBelongToUser(userLogged,userId)) {
            throw new ForbiddenException("Access denied");
        }
    }

    private boolean orderBelongToUser(User userLogged, Long userId){


        return userLogged.getId().equals(userId);
    }
}
