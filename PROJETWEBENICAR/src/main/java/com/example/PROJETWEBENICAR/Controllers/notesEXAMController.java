package com.example.PROJETWEBENICAR.Controllers;

import com.example.PROJETWEBENICAR.Entities.NotesCC;
import com.example.PROJETWEBENICAR.Entities.NotesExams;
import com.example.PROJETWEBENICAR.Repositories.ClassRepository;
import com.example.PROJETWEBENICAR.Repositories.NotesCCRepository;
import com.example.PROJETWEBENICAR.Repositories.NotesEXAMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notesEXAMS")
@CrossOrigin("*")
public class notesEXAMController {
    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private NotesEXAMRepository notesEXAMRepository;

    @PostMapping
    public ResponseEntity<NotesExams> giveNote(@RequestBody NotesExams note) {
        Long studentId = note.getStudent().getId();
        Long professorId = note.getProfessor().getId();
        Optional<NotesExams> existingNote = notesEXAMRepository.findByStudentIdAndProfessorId(studentId, professorId);
        if (existingNote.isPresent()) {
            // A note with the same student and professor already exists, return 409 Conflict response
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            // Save the note and return 201 Created response
            NotesExams savedNote = notesEXAMRepository.save(note);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedNote);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        Optional<NotesExams> userOptional = notesEXAMRepository.findById(id);
        if (userOptional.isPresent()) {
            notesEXAMRepository.delete(userOptional.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<NotesExams> getAllNotes() {
        return notesEXAMRepository.findAll();
    }



    @GetMapping("/student/{studentId}/professor/{professorId}")
    public ResponseEntity<notesEXAMController.NoteResponse> getNoteByStudentAndProfessor(@PathVariable Long studentId, @PathVariable Long professorId) {
        Optional<NotesExams> notesOptional = notesEXAMRepository.findByStudentIdAndProfessorId(studentId, professorId);
        if (notesOptional.isPresent()) {
            NotesExams NotesExams = notesOptional.get();
            notesEXAMController.NoteResponse noteResponse = new notesEXAMController.NoteResponse(NotesExams.getId(), NotesExams.getNote());
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
    @CrossOrigin("*")
    @PatchMapping("/{studentId}/{professorId}")
    public ResponseEntity<?> updateNotesEXAM(@PathVariable Long studentId, @PathVariable Long professorId, @RequestBody NotesExams notesExams) {
        Optional<NotesExams> optionalNotes = notesEXAMRepository.findByStudentIdAndProfessorId(studentId, professorId);
        if (optionalNotes.isPresent()) {
            NotesExams notes = optionalNotes.get();

            notes.setNote(notesExams.getNote());
            notesEXAMRepository.save(notes);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


}
