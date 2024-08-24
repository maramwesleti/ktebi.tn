package com.example.projet1.Controller;

import com.example.projet1.Entity.Livre;
import com.example.projet1.Entity.Utilisateur;
import com.example.projet1.service.LivreService;
import com.example.projet1.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
@SessionAttributes("utilisateurConnecte")
@RequestMapping("/")
public class loginController {

    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private LivreService livreService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/login"; // Assurez-vous de créer cette vue
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        Utilisateur utilisateur = utilisateurService.findByEmailAndMotDePasse(email, password);
        if (utilisateur != null) {
            model.addAttribute("utilisateurConnecte", utilisateur);
            return "redirect:/home"; // Redirige après une connexion réussie
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "Auth/creercompte";
        }
    }

    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "Auth/login"; // Redirige après une déconnexion
    }

    @GetMapping("/home")
    public String afficherHome(@ModelAttribute("utilisateurConnecte") Utilisateur utilisateur, Model model) {
        if (utilisateur != null) {
            String email = utilisateur.getEmail();
            String role = utilisateurService.getRoleUtilisateur(email);
            if (role.equals("user")) {
                return "redirect:/getlivre";
            } else if (role.equals("admin")) {
                return "redirect:/index";
            }
        }
        return "redirect:/creercompte";
    }

    @GetMapping("/getlivre")
    public String getAllLivres(Model model) {
        List<Livre> livres = livreService.getAllLivres();
        model.addAttribute("livres", livres);
        return "utilisateur/home"; // Remplacez "listlivre" par le nom de votre page affichant la liste des livres
    }

    @GetMapping("/creercompte")
    public String showAddForm(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "Auth/creercompte"; // Le nom de votre fichier auteurform.html
    }

    @PostMapping(value = "/adduser")
    public String creercompte(@ModelAttribute Utilisateur utilisateur) {
        utilisateurService.addUtilisateur(utilisateur);
        return "Auth/login"; // Redirection vers la page de gestion des livres
    }
}
