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

import ca.uqam.meritas.dto.EtudiantDto;
import ca.uqam.meritas.service.EtudiantService;

@RestController
public class EtudiantController {
	@Autowired
	private EtudiantService etudiantService;

	@CrossOrigin(origins ="http://meritas-ui.s3-website.us-east-2.amazonaws.com")
	//@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/etudiants")
	public List<EtudiantDto> obtenirEtudiants() {
		return etudiantService.obtenirTousEtudiants();
	}

	@CrossOrigin(origins ="http://meritas-ui.s3-website.us-east-2.amazonaws.com")
	//@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/etudiants")
	@ResponseStatus(HttpStatus.CREATED)
	public List<EtudiantDto> creerEtudiants(@RequestBody List<EtudiantDto> etudiants) {
		return etudiantService.creerEtudiants(etudiants);
	}

	@CrossOrigin(origins ="http://meritas-ui.s3-website.us-east-2.amazonaws.com")
	//@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/etudiants")
	public EtudiantDto changerEtudiantActifEnInactif(@RequestBody EtudiantDto etudiant) {
		return etudiantService.changerEtudiantActifEnInactif(etudiant);

	}
}
