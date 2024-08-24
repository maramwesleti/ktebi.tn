package com.example.projet1.repository;

import com.example.projet1.Entity.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuteurRepository extends JpaRepository<Auteur, Long> {
    // Vous pouvez ajouter des méthodes supplémentaires de requête si nécessaire
}

