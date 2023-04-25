package com.example.PROJETWEBENICAR.Repositories;


import com.example.PROJETWEBENICAR.Entities.NotesCC;
import com.example.PROJETWEBENICAR.Entities.NotesExams;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotesEXAMRepository extends JpaRepository<NotesExams, Long> {
    Optional<NotesExams> findById(Long id);
    Optional<NotesExams> findByStudentIdAndProfessorId(Long studentId, Long professorId);
}
