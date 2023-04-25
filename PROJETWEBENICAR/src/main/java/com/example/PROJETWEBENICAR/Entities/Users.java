package com.example.PROJETWEBENICAR.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
public class Users {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    private String role;
    public void setRole(String role) {
        this.role = role;
    }
    public String getRole(){
        return this.role;
    }


    private String password;
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
    private String Nom;



    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    private String Prénom;
    public String getPrénom() {
        return Prénom;
    }

    public void setPrénom(String prénom) {
        Prénom = prénom;
    }

    private int NumTel ;

    public int getNumTel() {
        return NumTel;
    }

    public void setNumTel(int numTel) {
        NumTel = numTel;
    }






    @Column(name = "adr_mail")
    private String adrMail;
    public String getAdrMail() {
        return adrMail;
    }

    public void setAdrMail(String adrMail) {
        this.adrMail = adrMail;
    }

    public List<Classes> getClasses() {
        return classes;
    }

    public void setClasses(List<Classes> classes) {
        this.classes = classes;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_classes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id"))
    @JsonIgnoreProperties("users")

    private List<Classes> classes = new ArrayList<>();
    @Column(name = "matiere")
    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    @Column(name="matiere")

    private String matiere ;

}

