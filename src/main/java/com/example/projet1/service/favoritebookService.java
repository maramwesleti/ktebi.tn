package com.example.projet1.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.projet1.Entity.Utilisateur;
import com.example.projet1.Entity.Livre;
import com.example.projet1.repository.UtilisateurRepository;
import com.example.projet1.repository.LivreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class favoritebookService implements IfavoritebookService{
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private LivreRepository livreRepository;

    @Transactional
    public void addLivreToFavorites(Long utilisateurId, Long livreId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId).orElseThrow(() -> new RuntimeException("Utilisateur not found"));
        Livre livre = livreRepository.findById(livreId).orElseThrow(() -> new RuntimeException("Livre not found"));

        utilisateur.getLivresFavoris().add(livre);
        livre.getUtilisateurs().add(utilisateur);

        utilisateurRepository.save(utilisateur);
    }
    @Transactional(readOnly = true)
    public Set<Livre> getFavoriteBooks(Long utilisateurId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId).orElseThrow(() -> new RuntimeException("Utilisateur not found"));
        // Initialize the collection to avoid LazyInitializationException

        return  utilisateur.getLivresFavoris() ;
    }
    public void removeLivreFromFavorites(Long utilisateurId, Long livreId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId).orElseThrow(() -> new RuntimeException("Utilisateur not found"));
        Livre livre = livreRepository.findById(livreId).orElseThrow(() -> new RuntimeException("Livre not found"));

        utilisateur.getLivresFavoris().remove(livre);
        livre.getUtilisateurs().remove(utilisateur);

        utilisateurRepository.save(utilisateur);
    }
}

