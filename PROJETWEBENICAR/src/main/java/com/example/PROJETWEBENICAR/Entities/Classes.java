package com.example.PROJETWEBENICAR.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.data.relational.core.sql.In;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="classes")
public class Classes {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private  long id;
    private String nom;

    private Integer niveau;
    @ManyToMany(mappedBy = "classes")

    private List<Users> users = new ArrayList<>();

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public String getDépartement() {
        return département;
    }

    public void setDépartement(String département) {
        this.département = département;
    }



    private String département;




    private String url; // Add this line to declare the "url" field



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;

    }

}
