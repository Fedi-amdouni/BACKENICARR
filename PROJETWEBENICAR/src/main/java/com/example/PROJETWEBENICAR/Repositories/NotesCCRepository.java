package com.example.PROJETWEBENICAR.Repositories;


import com.example.PROJETWEBENICAR.Entities.NotesCC;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotesCCRepository extends JpaRepository<NotesCC, Long> {
    Optional<NotesCC> findById(Long id);

    Optional<NotesCC> findByStudentIdAndProfessorId(Long studentId, Long professorId);
}
