
package com.example.projet1.service;

import com.example.projet1.Entity.Livre;
import java.util.Set;

public interface ILivretoreadService {

    void addLivreToRead(Long utilisateurId, Long livreId);

    Set<Livre> getLivreToRead(Long utilisateurId);

    void deleteterminateBook(Long utilisateurId, Long livreId);
}