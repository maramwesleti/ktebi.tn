package com.example.projet1.repository;
import com.example.projet1.Entity.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {
    // Vous pouvez ajouter des méthodes supplémentaires de requête si nécessaire

}
