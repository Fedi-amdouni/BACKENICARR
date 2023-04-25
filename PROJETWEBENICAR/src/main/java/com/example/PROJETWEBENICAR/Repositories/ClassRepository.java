package com.example.PROJETWEBENICAR.Repositories;

import com.example.PROJETWEBENICAR.Entities.Classes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRepository extends JpaRepository<Classes, Long> {


    List<Classes> findByUsers(Classes classes);
    List<Classes> findAllByOrderByNom();

    Classes findByNom(String nom);
}
