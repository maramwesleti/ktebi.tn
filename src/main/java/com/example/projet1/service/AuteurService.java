package com.example.projet1.service;

import com.example.projet1.Entity.Auteur;
import com.example.projet1.repository.AuteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuteurService implements IAuteurService {

    private final AuteurRepository auteurRepository;

    @Autowired
    public AuteurService(AuteurRepository auteurRepository) {
        this.auteurRepository = auteurRepository;
    }

    public List<Auteur> getAllAuteurs() {
        return auteurRepository.findAll();
    }

    public Auteur getAuteurById(Long id) {
        Optional<Auteur> auteurOptional = auteurRepository.findById(id);
        return auteurOptional.orElse(null);
    }

    public Auteur addAuteur(Auteur auteur) {
        return auteurRepository.save(auteur);
    }

    public Auteur updateAuteur(Long id, Auteur auteur) {
        if (auteurRepository.existsById(id)) {
            auteur.setId(id);
            return auteurRepository.save(auteur);
        } else {
            return null; // Ou lancez une exception appropriée si nécessaire
        }
    }


    public void deleteAuteur(Long id) {
        if (auteurRepository.existsById(id)) {
            auteurRepository.deleteById(id);
        } else {
            // Gérer le cas où l'auteur n'existe pas
        }
    }

    public List<Auteur> getAuteursByIds(List<Long> ids) {
        return auteurRepository.findAllById(ids);
    }
}
