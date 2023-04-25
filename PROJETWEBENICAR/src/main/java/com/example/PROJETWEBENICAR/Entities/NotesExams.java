package com.example.PROJETWEBENICAR.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="notesEXAM")
public class NotesExams {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id ;
    private String subject;
    private float note;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Users student;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id", nullable = false)
    private Users professor;

    public Users getStudent() {
        return student;
    }

    public void setStudent(Users student) {
        this.student = student;
    }

    public Users getProfessor() {
        return professor;
    }

    public void setProfessor(Users professor) {
        this.professor = professor;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", nullable = false)
    private Classes classes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }



    public Classes getClasses() {
        return classes;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }
}
