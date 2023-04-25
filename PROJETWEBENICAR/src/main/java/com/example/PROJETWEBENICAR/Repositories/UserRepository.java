package com.example.PROJETWEBENICAR.Repositories;

import com.example.PROJETWEBENICAR.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findById(Long id);
    Users findByAdrMail(String adrMail);

    Optional<Users> findByMatiereIgnoreCase(String Matiere);
}