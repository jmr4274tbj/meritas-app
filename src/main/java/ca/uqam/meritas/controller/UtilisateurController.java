package ca.uqam.meritas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ca.uqam.meritas.dto.UtilisateurDto;
import ca.uqam.meritas.service.UtilisateurService;

@RestController
public class UtilisateurController {

	@Autowired
	private UtilisateurService utilisateurService;

	// @CrossOrigin(origins =
	// "http://meritas-ui.s3-website.us-east-2.amazonaws.com")
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/utilisateurs")
	public List<UtilisateurDto> obtenirUtilisateurs() {
		return utilisateurService.obtenirTousUtilisateurs();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/utilisateurs")
	@ResponseStatus(HttpStatus.CREATED)
	public UtilisateurDto creerUtilisateur(@RequestBody UtilisateurDto utilisateur) {
		return utilisateurService.creerUtilisateur(utilisateur);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/utilisateurs-valider")
	public UtilisateurDto validerUtilisateur(@RequestBody UtilisateurDto utilisateur) {
		return utilisateurService.validerUtilisateur(utilisateur);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/utilisateurs")
	public UtilisateurDto modifierUtilisateur(@RequestBody UtilisateurDto utilisateur) {
		return utilisateurService.creerUtilisateur(utilisateur);
	}
}
