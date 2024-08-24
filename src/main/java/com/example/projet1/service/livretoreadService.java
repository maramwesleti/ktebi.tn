package com.example.projet1.service;
import com.example.projet1.Entity.Livre;
import com.example.projet1.Entity.Utilisateur;
import com.example.projet1.repository.LivreRepository;
import com.example.projet1.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class livretoreadService implements ILivretoreadService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private LivreRepository livreRepository;
    @Transactional
    public void addLivreToRead(Long utilisateurId, Long livreId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur not found"));
        Livre livre = livreRepository.findById(livreId)
                .orElseThrow(() -> new RuntimeException("Livre not found"));

        utilisateur.getLivrestoread().add(livre); // Utilisation de getLivrestoread() ici
        livre.getUtilisateurs2().add(utilisateur); // Correction pour getUtilisateurs2()

        utilisateurRepository.save(utilisateur);
    }

    @Transactional(readOnly = true)
    public Set<Livre> getLivreToRead(Long utilisateurId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId).orElseThrow(() -> new RuntimeException("Utilisateur not found"));
        // Initialize the collection to avoid LazyInitializationException

        return  utilisateur.getLivrestoread() ;
    }
    public void deleteterminateBook(Long utilisateurId, Long livreId) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId).orElseThrow(() -> new RuntimeException("Utilisateur not found"));
        Livre livre = livreRepository.findById(livreId).orElseThrow(() -> new RuntimeException("Livre not found"));

        utilisateur.getLivrestoread().remove(livre);
        livre.getUtilisateurs().remove(utilisateur);

        utilisateurRepository.save(utilisateur);
    }


}

