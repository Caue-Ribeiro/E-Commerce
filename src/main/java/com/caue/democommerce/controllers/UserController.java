package com.caue.democommerce.controllers;

import com.caue.democommerce.dto.UserDTO;
import com.caue.democommerce.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/me")
    public ResponseEntity<UserDTO> getMyUser(){

        UserDTO user = service.getMyUser();

        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserDTO> insertUser(@RequestBody UserDTO dto){
        UserDTO newUser = service.insertUser(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();
        return ResponseEntity.created(uri).body(newUser);
    }
}
