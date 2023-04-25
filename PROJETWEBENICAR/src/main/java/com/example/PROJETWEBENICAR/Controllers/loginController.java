package com.example.PROJETWEBENICAR.Controllers;

import com.example.PROJETWEBENICAR.Entities.Users;
import com.example.PROJETWEBENICAR.Repositories.UserRepository;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class loginController {

    private final UserRepository userRepository;

    public loginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @CrossOrigin("*")
    @PostMapping("/login")
    public ResponseEntity<Users> login(@RequestBody LoginRequest loginRequest) {
        Users user = userRepository.findByAdrMail(loginRequest.getEmail());
        if (user == null || !user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.ok(user);
    }





    // other endpoints for user management
}
