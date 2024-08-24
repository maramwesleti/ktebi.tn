package com.example.projet1.Controller;

import com.example.projet1.Entity.Livre;
import com.example.projet1.Entity.Utilisateur;
import com.example.projet1.service.UtilisateurService;
import com.example.projet1.service.favoritebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.projet1.service.livretoreadService;

import java.util.List;
import java.util.Set;

@Controller
@SessionAttributes("utilisateurConnecte")
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    @Autowired
    private livretoreadService livretoreadService;
    private final UtilisateurService utilisateurService;
    @Autowired
    private favoritebookService favoritebookService ;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService, favoritebookService favoritebookService) {
        this.utilisateurService = utilisateurService;
        this.favoritebookService = favoritebookService;
        this.livretoreadService=livretoreadService;
    }

    // Endpoint pour récupérer tous les utilisateurs
    @GetMapping("/retrieve-all-utilisateurs")
    public String getAllUtilisateurs(Model model) {
        List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateurs();
        model.addAttribute("utilisateurs", utilisateurs);
        return "utilisateur/listutilisateur"; // Remplacez "listutilisateur" par le nom de votre page affichant la liste des utilisateurs
    }

    // Endpoint pour récupérer un utilisateur par son ID
    @GetMapping("/details/{id}")
    public String getUtilisateurById(@PathVariable Long id, Model model) {
        Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);
        model.addAttribute("utilisateur", utilisateur);
        return "utilisateur/detailsutilisateur"; // Remplacez "detailsutilisateur" par le nom de votre page affichant les détails de l'utilisateur
    }

    // Endpoint pour ajouter un nouvel utilisateur (affiche un formulaire)
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "utilisateur/utilisateurform"; // Remplacez "utilisateurform" par le nom de votre formulaire d'ajout d'utilisateur
    }

    // Endpoint pour traiter l'ajout d'un nouvel utilisateur
    @PostMapping("/addutilisateur")
    public String addUtilisateur(@ModelAttribute Utilisateur utilisateur) {
        utilisateurService.addUtilisateur(utilisateur);
        return "redirect:/utilisateurs/retrieve-all-utilisateurs";
    }

    @GetMapping("/update")
    public String showUpdateForm(@RequestParam("utilisateurId") Long id, Model model) {
        Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);
        model.addAttribute("utilisateur", utilisateur);
        return "utilisateur/updateutilisateur"; // Remplacez "updateutilisateur" par le nom de votre formulaire de mise à jour d'utilisateur
    }

    @PostMapping("/update")
    public String updateUtilisateur(@ModelAttribute Utilisateur utilisateur) {
        utilisateurService.updateUtilisateur(utilisateur.getId(), utilisateur);
        return "redirect:/utilisateurs/retrieve-all-utilisateurs";
    }


    @GetMapping("/delete")
    public String deleteUtilisateur(@RequestParam("utilisateurId") Long id) {
        utilisateurService.deleteUtilisateur(id);
        return "redirect:/utilisateurs/retrieve-all-utilisateurs";
    }

    @PostMapping("/addLivrefavoris")
    public String addLivrefavoris(@RequestParam("livreId") Long livreId, @ModelAttribute("utilisateurConnecte") Utilisateur utilisateur, Model model) {
        favoritebookService.addLivreToFavorites(utilisateur.getId(), livreId);
        model.addAttribute("utilisateurId", utilisateur.getId()); // Ajoute l'ID de l'utilisateur au modèle
        return "redirect:/utilisateurs/listfavoris/" + utilisateur.getId(); // Redirige vers la liste des favoris avec l'ID de l'utilisateur
    }

    @GetMapping("/listfavoris/{id}")
    public String listFavoris(@PathVariable Long id, Model model) {
        Set<Livre> livresFavoris = favoritebookService.getFavoriteBooks(id);
        model.addAttribute("livresFavoris", livresFavoris);
        return "utilisateur/listfavoris";
    }

    @GetMapping("/user/{utilisateurId}/book/{livreId}")
    public ResponseEntity<Void> deleteFavoriteBook(@PathVariable Long utilisateurId, @PathVariable Long livreId) {
        favoritebookService.removeLivreFromFavorites(utilisateurId, livreId);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }


    @PostMapping("/addLivretoread")
    public String addLivretoread(@RequestParam("livreId") Long livreId, @ModelAttribute("utilisateurConnecte") Utilisateur utilisateur, Model model) {
        livretoreadService.addLivreToRead(utilisateur.getId(), livreId);
        model.addAttribute("utilisateurId", utilisateur.getId()); // Ajoute l'ID de l'utilisateur au modèle
        return "redirect:/utilisateurs/listtoread/" + utilisateur.getId(); // Redirige vers la liste des favoris avec l'ID de l'utilisateur
    }

    @GetMapping("/listtoread/{id}")
    public String listtoread(@PathVariable Long id, Model model) {
        Set<Livre> livrestoread = livretoreadService.getLivreToRead(id);
        model.addAttribute("livrestoread", livrestoread);
        return "utilisateur/listtoread";
    }
    @GetMapping("/user/{utilisateurId}/livreterminee/{livreId}")
    public ResponseEntity<Void> deleteterminateBook(@PathVariable Long utilisateurId, @PathVariable Long livreId) {
        livretoreadService.deleteterminateBook(utilisateurId, livreId);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }


}
