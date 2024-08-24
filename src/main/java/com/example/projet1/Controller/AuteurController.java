package com.example.projet1.Controller;
import com.example.projet1.Entity.Auteur;
import com.example.projet1.service.AuteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/auteur")
public class AuteurController {

    @Autowired
    AuteurService auteurService;
    // Endpoint pour récupérer tous les auteurs
    @GetMapping("/retrieve-all-auteurs")
    public String getAllAuteurs(Model model) {
        List<Auteur> listAuteurs = auteurService.getAllAuteurs();
        model.addAttribute("listAuteurs",listAuteurs);
        return "auteur/listauteur";

    }
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("auteur", new Auteur()); // Auteur est votre classe de modèle
        return "auteur/auteurform"; // Le nom de votre fichier auteurform.html
    }

    @PostMapping("/addAuteur")
    public String addAuteur(@ModelAttribute Auteur auteur, RedirectAttributes redirectAttributes) {
        auteurService.addAuteur(auteur);
        redirectAttributes.addFlashAttribute("successMessage", "Auteur ajouté avec succès!");
        return "redirect:/auteur/retrieve-all-auteurs"; // Redirection vers la page qui affiche tous les auteurs
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("auteurId") Long id) {

        auteurService.deleteAuteur(id);
        return "redirect:/auteur/retrieve-all-auteurs";
    }

    @GetMapping("/update")
    public String updateAuteur(@RequestParam("auteurId") Long id, Model model) {
        // Récupérer l'auteur à partir du service
        Auteur auteur = auteurService.getAuteurById(id);
        // Ajouter l'auteur en tant qu'attribut de modèle pour pré-remplir le formulaire
        model.addAttribute("auteur", auteur);
        // Envoyer vers notre formulaire
        return "auteur/updateauteur";
    }

    @PostMapping("/updateAuteur")
    public String updateAuteur(@ModelAttribute Auteur auteur) {
        // Mettre à jour l'auteur à l'aide du service
        Auteur updatedAuteur = auteurService.updateAuteur(auteur.getId(), auteur);
        // Rediriger vers la page qui affiche tous les auteurs
        return "redirect:/auteur/retrieve-all-auteurs";
    }


}


