package com.example.PROJETWEBENICAR.Controllers;

import com.example.PROJETWEBENICAR.Entities.NotesCC;
import com.example.PROJETWEBENICAR.Entities.NotesExams;
import com.example.PROJETWEBENICAR.Repositories.ClassRepository;
import com.example.PROJETWEBENICAR.Repositories.NotesCCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notesCC")
@CrossOrigin("*")
public class notesCCController {
    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private NotesCCRepository notesCCRepository;

    @PostMapping
    public ResponseEntity<NotesCC> giveNote(@RequestBody NotesCC note) {
        Long studentId = note.getStudent().getId();
        Long professorId = note.getProfessor().getId();
        Optional<NotesCC> existingNote = notesCCRepository.findByStudentIdAndProfessorId(studentId, professorId);
        if (existingNote.isPresent()) {
            // A note with the same student and professor already exists, return 409 Conflict response
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            // Save the note and return 201 Created response
            NotesCC savedNote = notesCCRepository.save(note);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedNote);
        }
    }



    @GetMapping
    public List<NotesCC> getAllNotes() {
        return notesCCRepository.findAll();
    }

    @GetMapping("/student/{studentId}/professor/{professorId}")
    public ResponseEntity<NoteResponse> getNoteByStudentAndProfessor(@PathVariable Long studentId, @PathVariable Long professorId) {
        Optional<NotesCC> notesOptional = notesCCRepository.findByStudentIdAndProfessorId(studentId, professorId);
        if (notesOptional.isPresent()) {
            NotesCC notesCC = notesOptional.get();
            NoteResponse noteResponse = new NoteResponse(notesCC.getId(), notesCC.getNote());
            return ResponseEntity.ok(noteResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public static class NoteResponse {
        private Long id;
        private Float note;

        public NoteResponse(Long id, Float note) {
            this.id = id;
            this.note = note;
        }

        public Long getId() {
            return id;
        }

        public Float getNote() {
            return note;
        }
    }
    @PatchMapping("/{studentId}/{professorId}")
    public ResponseEntity<?> updateNotescc(@PathVariable Long studentId, @PathVariable Long professorId, @RequestBody NotesCC notesCC) {
        Optional<NotesCC> optionalNotes = notesCCRepository.findByStudentIdAndProfessorId(studentId, professorId);
        if (optionalNotes.isPresent()) {
            NotesCC notes = optionalNotes.get();

            notes.setNote(notesCC.getNote());
            notesCCRepository.save(notes);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }



}
