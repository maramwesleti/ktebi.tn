

package com.example.projet1.service;

import com.example.projet1.Entity.Livre;
import java.util.Set;

public interface IfavoritebookService {

    void addLivreToFavorites(Long utilisateurId, Long livreId);

    Set<Livre> getFavoriteBooks(Long utilisateurId);

    void removeLivreFromFavorites(Long utilisateurId, Long livreId);
}