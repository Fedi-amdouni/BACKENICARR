package com.example.PROJETWEBENICAR.Controllers;

import com.example.PROJETWEBENICAR.Entities.Classes;
import com.example.PROJETWEBENICAR.Entities.Users;
import com.example.PROJETWEBENICAR.Repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/classes")
@CrossOrigin("*")
public class classController {
    @Autowired
private ClassRepository classRepository;
    @PostMapping
    public Classes createClass(@RequestBody Classes classe) {
        String nom = classe.getNom();
        Classes existingClass = classRepository.findByNom(nom);
        if (existingClass != null) {
            // The class already exists, so you can return an error response or handle it in another way
            // For example, you can throw an exception to indicate that the class already exists
            throw new IllegalArgumentException("A class with the name '" + nom + "' already exists");
        }
        return classRepository.save(classe);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        Optional<Classes> userOptional = classRepository.findById(id);
        if (userOptional.isPresent()) {
            classRepository.delete(userOptional.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping
    public List<Classes> listClasses() {
        return classRepository.findAllByOrderByNom();
    }

    @GetMapping("/{id}/students")
    public List<Users> getClassStudents(@PathVariable Long id) {
        Optional<Classes> classOptional = classRepository.findById(id);
        if (classOptional.isPresent()) {
            Classes classe = classOptional.get();
            List<Users> students = classe.getUsers().stream()
                    .filter(user -> user.getRole().equals("Etudiant"))
                    .collect(Collectors.toList());
            return students;
        } else {
            throw new ResourceNotFoundException("Class not found with id " + id);
        }
    }

    @GetMapping("/{id}/professeurs")
    public List<Users> getClassProfesseurs(@PathVariable Long id) {
        Optional<Classes> classOptional = classRepository.findById(id);
        if (classOptional.isPresent()) {
            Classes classe = classOptional.get();
            List<Users> students = classe.getUsers().stream()
                    .filter(user -> user.getRole().equals("Professeur"))
                    .collect(Collectors.toList());
            return students;
        } else {
            throw new ResourceNotFoundException("Class not found with id " + id);
        }
    }
    @GetMapping("/{classId}/matieres")
    public List<String> getMatieresByClassId(@PathVariable Long classId) {
        List<String> matieres = new ArrayList<>();
        Optional<Classes> optionalClass = classRepository.findById(classId);
        if (optionalClass.isPresent()) {
            Classes classObj = optionalClass.get();
            List<Users> users = classObj.getUsers();
            for (Users user : users) {
                if (user.getMatiere() != null && !user.getMatiere().isEmpty()) {
                    matieres.add(user.getMatiere());
                }
            }
        }
        return matieres;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Classes> updateClass(@PathVariable Long id, @RequestBody Classes updatedClass) {
        Optional<Classes> classOptional = classRepository.findById(id);
        if (classOptional.isPresent()) {
            Classes classToBeUpdated = classOptional.get();
            classToBeUpdated.setNom(updatedClass.getNom());
            classToBeUpdated.setNiveau(updatedClass.getNiveau());
            classToBeUpdated.setDépartement(updatedClass.getDépartement());
            classRepository.save(classToBeUpdated);
            return ResponseEntity.ok(classToBeUpdated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }












}
