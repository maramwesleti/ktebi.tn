package com.example.projet1.service;
import com.example.projet1.Entity.Auteur;
import com.example.projet1.Entity.Livre;
import com.example.projet1.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreService implements ILivreService {

    private final LivreRepository livreRepository;
    private final AuteurService auteurService;

    @Autowired
    public LivreService(LivreRepository livreRepository, AuteurService auteurService) {
        this.livreRepository = livreRepository;
        this.auteurService = auteurService; // Injection du service Auteur
    }

    public Livre addLivreWithNewAuteur(Livre livre, Auteur nouvelAuteur) {
        // Enregistrer d'abord le nouvel auteur
        Auteur savedAuteur = auteurService.addAuteur(nouvelAuteur);
        // Ajouter le nouvel auteur à la liste des auteurs du livre
        livre.getAuteurs().add(savedAuteur);
        // Enregistrer le livre dans la base de données
        return livreRepository.save(livre);
    }

    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    public Livre getLivreById(Long id) {
        return livreRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Livre non trouvé avec l'ID : " + id));
    }

    public Livre addLivre(Livre livre) {
        // Ajoutez ici la logique pour valider le livre (par exemple, vérifier les champs obligatoires)
        return livreRepository.save(livre);
    }

    public Livre updateLivre(Long id, Livre livre) {
        // Vérifiez si le livre avec cet ID existe
        if (!livreRepository.existsById(id)) {
            throw new IllegalArgumentException("Livre non trouvé avec l'ID : " + id);
        }
        // Ajoutez ici la logique pour mettre à jour le livre
        livre.setId(id);
        return livreRepository.save(livre);
    }

    public void deleteLivre(Long id) {
        // Vérifiez si le livre avec cet ID existe
        if (!livreRepository.existsById(id)) {
            throw new IllegalArgumentException("Livre non trouvé avec l'ID : " + id);
        }
        livreRepository.deleteById(id);
    }

    public String getContenuById(Long id) {
        // Utilisez le repository pour accéder aux données du livre par son ID
        Livre livre = livreRepository.findById(id).orElse(null);


            // Récupérez le contenu du livre
            return livre.getContenu();


    }

    public String getImageById(Long id) {
        Livre livre = livreRepository.findById(id).orElse(null);


        // Récupérez le contenu du livre
        return livre.getImage();

    }
}