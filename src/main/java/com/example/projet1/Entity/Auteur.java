package com.example.projet1.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity()
@Table(name ="Auteur")
@Setter
@Getter
@ToString
public class Auteur implements Serializable {
    private static final long SerialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    private String nom;
    private String prenom;
    private LocalDate dateNaissance;




    @ManyToMany(mappedBy = "auteurs")
    private List<Livre> livres = new ArrayList<>();

    // Getters et Setters
}

