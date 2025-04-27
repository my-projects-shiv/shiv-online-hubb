package com.shivonlinehub.dbcomponent.controller;

import com.shivonlinehub.dbcomponent.dto.UserDTO;
import com.shivonlinehub.dbcomponent.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDTO userDTO) {
        return authService.register(userDTO);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody UserDTO userDTO) {
        return authService.login(userDTO);
    }
}
