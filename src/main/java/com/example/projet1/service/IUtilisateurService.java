

package com.example.projet1.service;

import com.example.projet1.Entity.Utilisateur;
import java.util.List;

public interface IUtilisateurService {

    List<Utilisateur> getAllUtilisateurs();

    Utilisateur getUtilisateurById(Long id);

    Utilisateur addUtilisateur(Utilisateur utilisateur);

    Utilisateur updateUtilisateur(Long id, Utilisateur utilisateur);

    void deleteUtilisateur(Long id);

    boolean existeUtilisateur(String email, String password);

    String getRoleUtilisateur(String email);

    Utilisateur findByEmailAndMotDePasse(String email, String motDePasse);
}