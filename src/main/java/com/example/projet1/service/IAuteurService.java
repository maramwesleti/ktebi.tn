

package com.example.projet1.service;

import com.example.projet1.Entity.Auteur;
import java.util.List;

public interface IAuteurService {

    List<Auteur> getAllAuteurs();

    Auteur getAuteurById(Long id);

    Auteur addAuteur(Auteur auteur);

    Auteur updateAuteur(Long id, Auteur auteur);

    void deleteAuteur(Long id);

    List<Auteur> getAuteursByIds(List<Long> ids);
}