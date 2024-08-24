package com.example.projet1.repository;
import com.example.projet1.Entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    // Vous pouvez ajouter des méthodes supplémentaires de requête si nécessaire
    Optional<Utilisateur> findByEmail(String email);


    Utilisateur findByEmailAndMotDePasse(String email, String motDePasse);
}
