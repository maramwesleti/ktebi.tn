package com.example.projet1.Controller;
import com.example.projet1.Entity.Auteur;
import com.example.projet1.Entity.Livre;
import com.example.projet1.Entity.Utilisateur;
import com.example.projet1.repository.LivreRepository;
import com.example.projet1.repository.UtilisateurRepository;
import com.example.projet1.service.AuteurService;
import com.example.projet1.service.UtilisateurService;
import com.example.projet1.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("utilisateurConnecte")
@RequestMapping("/livres")
public class LivreController {

    private final LivreService livreService;
    private final AuteurService auteurService;
    private final LivreRepository livreRepository;
    private final UtilisateurRepository utilisateurRepository;


    private final  UtilisateurService utilisateurService;
    @Autowired
    public LivreController(LivreService livreService, AuteurService auteurService, LivreRepository livreRepository, com.example.projet1.service.favoritebookService favoritebookservice, UtilisateurRepository utilisateurRepository, UtilisateurService utilisateurService) {
        this.livreService = livreService;
        this.auteurService = auteurService;
        this.livreRepository = livreRepository;
        this.utilisateurRepository = utilisateurRepository;

        this.utilisateurService = utilisateurService;
    }


    @GetMapping("/retrieve-all-livres")
    public String getAllLivres(Model model) {


        List<Livre> livres = livreService.getAllLivres();
        model.addAttribute("livres", livres);
        return "livre/listlivre"; // Remplacez "listlivre" par le nom de votre page affichant la liste des livres
    }


    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("livre", new Livre());
        List<Auteur> auteurs = auteurService.getAllAuteurs(); // Récupérer la liste des auteurs
        model.addAttribute("auteurs", auteurs); // Ajouter la liste des auteurs au modèle
        return "livre/livreform";
    }
    @PostMapping("/addLivre")
    public String addLivre(@ModelAttribute Livre livre) {
            livreService.addLivre(livre);
        return "redirect:/livres/retrieve-all-livres";
    }


    @GetMapping("/delete")
    public String deleteLivre(@RequestParam("livreId") Long id) {
        livreService.deleteLivre(id);
        return "redirect:/livres/retrieve-all-livres";
    }

    @GetMapping("/update")
    public String updateLivre1(@RequestParam("livreId") Long id, Model model) {
        Livre livre = livreService.getLivreById(id);
        model.addAttribute("livre", livre);
        List<Auteur> auteurs = auteurService.getAllAuteurs(); // Récupérer la liste des auteurs
        model.addAttribute("auteurs", auteurs);
        return "livre/updateLivre"; // Remplacez "updateLivre" par le nom de votre formulaire pour mettre à jour un livre
    }


    @PostMapping("/updateLivre")
    public String updateLivre(@ModelAttribute Livre livre, @RequestParam("auteurIds") List<Long> auteurIds) {
        // Récupérer les auteurs sélectionnés à partir des IDs
        List<Auteur> auteurs = auteurService.getAuteursByIds(auteurIds);

        // Associer les auteurs sélectionnés au livre
        livre.setAuteurs(auteurs);

        // Mettre à jour le livre dans la base de données
        livreService.updateLivre(livre.getId(), livre);

        return "redirect:/livres/retrieve-all-livres";
    }


    @GetMapping("/contenu")
    public String getContenuLivre(@RequestParam("livreId") Long id, Model model) {
        // Supposons que vous avez une méthode dans votre service pour obtenir le contenu du livre par son ID
        Livre livre = livreService.getLivreById(id);
        model.addAttribute("livre", livre);
        String contenu = livreService.getContenuById(id);

        // Ajoutez le contenu à l'objet modèle pour l'afficher dans la vue
        model.addAttribute("contenu", contenu);
        String image=livreService.getImageById(id);
        model.addAttribute("image", image);
        // Retourne le nom de la vue à afficher (à remplacer par votre propre vue)
        return "livre/contenu";
    }
}
