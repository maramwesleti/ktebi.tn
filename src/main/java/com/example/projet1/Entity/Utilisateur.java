package com.example.projet1.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="Utilisateur")
@Setter
@Getter
@ToString
public class Utilisateur implements Serializable {
    private static final long SerialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String role ="user";



    // Getters et Setters


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "favorite_book",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "livre_id")
    )
    private Set<Livre> livresFavoris = new HashSet<>();


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "to_read_book",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "livre_id")
    )
    private Set<Livre> livrestoread = new HashSet<>();


}
