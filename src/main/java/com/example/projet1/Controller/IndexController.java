package com.example.projet1.Controller;
import com.example.projet1.service.AuteurService;
import com.example.projet1.service.LivreService;
import com.example.projet1.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private AuteurService auteurService;

    private LivreService livreService ;
    private UtilisateurService utilisateurService;

    // Mapping pour la page d'accueil
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        // Ajoutez ici la logique pour récupérer des données à afficher sur la page d'accueil
        return "index";
    }

    // Mapping pour la page de gestion des auteurs
    @RequestMapping(value = "/listauteur", method = RequestMethod.GET)
    public String gestionAuteurs(Model model) {
        // Ajoutez ici la logique pour récupérer les auteurs à afficher sur la page
        // Par exemple :
        // List<Auteur> auteurs = auteurService.getAllAuteurs();
        // model.addAttribute("auteurs", auteurs);
        return "redirect:/auteurs/retrieve-all-auteurs"; // Remplacez "gestion-auteurs" par le nom de votre page de gestion des auteurs
    }

    // Mapping pour la page de gestion des livres
    @RequestMapping(value = "/listlivre", method = RequestMethod.GET)
    public String gestionLivres(Model model) {
        // Ajoutez ici la logique pour récupérer les livres à afficher sur la page
        return "gestion-livres"; // Remplacez "gestion-livres" par le nom de votre page de gestion des livres
    }

    // Mapping pour la page de tableau de bord
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(Model model) {
        // Ajoutez ici la logique pour récupérer les données du tableau de bord
        return "dashboard"; // Remplacez "dashboard" par le nom de votre page de tableau de bord
    }



}
