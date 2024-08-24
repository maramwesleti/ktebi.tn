package com.example.projet1.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
    @Table(name = "livre_auteur")
    @Getter
    @Setter
    public class LivreAuteur implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "livre_id")
        private Livre livre;

        @ManyToOne
        @JoinColumn(name = "auteur_id")
        private Auteur auteur;

        // Constructeurs, getters et setters
    }


