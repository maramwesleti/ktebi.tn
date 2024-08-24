package com.example.projet1.service;

import com.example.projet1.Entity.Utilisateur;
import com.example.projet1.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService implements IUtilisateurService{

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé avec l'ID : " + id));
    }

    public Utilisateur addUtilisateur(Utilisateur utilisateur) {
        // Ajoutez ici la logique pour valider l'utilisateur (par exemple, vérifier les champs obligatoires)
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur updateUtilisateur(Long id, Utilisateur utilisateur) {
        // Vérifiez si l'utilisateur avec cet ID existe
        if (!utilisateurRepository.existsById(id)) {
            throw new IllegalArgumentException("Utilisateur non trouvé avec l'ID : " + id);
        }
        // Ajoutez ici la logique pour mettre à jour l'utilisateur
        utilisateur.setId(id);
        return utilisateurRepository.save(utilisateur);
    }

    public void deleteUtilisateur(Long id) {
        // Vérifiez si l'utilisateur avec cet ID existe
        if (!utilisateurRepository.existsById(id)) {
            throw new IllegalArgumentException("Utilisateur non trouvé avec l'ID : " + id);
        }
        utilisateurRepository.deleteById(id);
    }


        public boolean existeUtilisateur(String email, String password) {
            Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findByEmail(email);
            return utilisateurOptional.isPresent() && utilisateurOptional.get().getMotDePasse().equals(password);
        }





    public String getRoleUtilisateur(String email) {
        // Recherche de l'utilisateur par ID
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findByEmail(email);

        // Vérification si l'utilisateur existe
        if (utilisateurOptional.isPresent()) {
            Utilisateur utilisateur = utilisateurOptional.get();
            // Retourne le rôle de l'utilisateur
            return utilisateur.getRole();
        } else {
            // Gérer le cas où l'utilisateur n'existe pas
            return null;
        }
    }

    public Utilisateur findByEmailAndMotDePasse(String email, String motDePasse) {
        return utilisateurRepository.findByEmailAndMotDePasse(email, motDePasse);
    }
}
