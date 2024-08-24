package com.example.projet1.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity()
@Table(name ="Livre")
@Setter
@Getter
@ToString
public class Livre implements Serializable {
    private static final long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String titre;
    private LocalDate datePublication;
    @Getter
    private String contenu;
    @Column
    private String categorie;
    @Column(nullable=true,length=664)
    private String image;

    @ManyToMany
    @JoinTable(
            name = "livre_auteur",
            joinColumns = @JoinColumn(name = "livre_id"),
            inverseJoinColumns = @JoinColumn(name = "auteur_id")
    )
    private List<Auteur> auteurs = new ArrayList<>();
    @ManyToMany(mappedBy = "livresFavoris")
    private Set<Utilisateur> utilisateurs = new HashSet<>();
    // Getters et Setters
    @ManyToMany(mappedBy = "livrestoread")
    private Set<Utilisateur> utilisateurs2 = new HashSet<>();
}

