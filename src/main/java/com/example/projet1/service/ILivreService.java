
package com.example.projet1.service;

import com.example.projet1.Entity.Auteur;
import com.example.projet1.Entity.Livre;
import java.util.List;

public interface ILivreService {

    Livre addLivreWithNewAuteur(Livre livre, Auteur nouvelAuteur);

    List<Livre> getAllLivres();

    Livre getLivreById(Long id);

    Livre addLivre(Livre livre);

    Livre updateLivre(Long id, Livre livre);

    void deleteLivre(Long id);

    String getContenuById(Long id);

    String getImageById(Long id);
}

