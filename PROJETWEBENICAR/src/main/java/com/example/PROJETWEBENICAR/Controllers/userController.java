package com.example.PROJETWEBENICAR.Controllers;

import com.example.PROJETWEBENICAR.Entities.Users;
import com.example.PROJETWEBENICAR.Repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")

public class userController {
    @Autowired
    private UserRepository userRepository;
    public void UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public Users createUser(@RequestBody Users user) {
        if(userRepository.findByAdrMail(user.getAdrMail()) != null) {
            // A user with the same email address already exists
            throw new IllegalArgumentException("A user with the same email address already exists");
        }
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        Optional<Users> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/matiere/{Matiere}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Users> getUserByMatiere(@PathVariable String Matiere) {
        Optional<Users> userOptional = userRepository.findByMatiereIgnoreCase( Matiere);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users updatedUser) {
        Optional<Users> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            user.setRole(updatedUser.getRole());
            user.setPassword(updatedUser.getPassword());
            user.setNom(updatedUser.getNom());
            user.setPrénom(updatedUser.getPrénom());
            user.setNumTel(updatedUser.getNumTel());
            user.setAdrMail(updatedUser.getAdrMail());
            user.setClasses(updatedUser.getClasses());
            user.setMatiere(updatedUser.getMatiere());
            userRepository.save(user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<Users> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }






}
