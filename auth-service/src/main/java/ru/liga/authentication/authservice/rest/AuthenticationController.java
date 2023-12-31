package ru.liga.authentication.authservice.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.liga.authentication.authservice.dto.RegDto;
import ru.liga.authentication.authservice.dto.RoleDto;
import ru.liga.authentication.authservice.service.UserService;


@RestController
@AllArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody RegDto request) {
        return userService.createUser(request);
    }

    @PostMapping("/role")
    public ResponseEntity<String> createRole(@RequestBody RoleDto request) {
        return userService.createRole(request);
    }

}