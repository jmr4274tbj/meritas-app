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

import ca.uqam.meritas.dto.RaisonActifDto;
import ca.uqam.meritas.service.RaisonActifService;

@RestController
public class RaisonActifController {

	@Autowired
	private RaisonActifService raisonActifService;

	@CrossOrigin(origins ="http://meritas-ui.s3-website.us-east-2.amazonaws.com")
	//@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/raison-actif")
	public List<RaisonActifDto> obtenirRaisons() {
		return raisonActifService.obtenirTousRaisons();
	}

	@CrossOrigin(origins ="http://meritas-ui.s3-website.us-east-2.amazonaws.com")
	//@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/raison-actif")
	@ResponseStatus(HttpStatus.CREATED)
	public RaisonActifDto creerNouvelleRaison(@RequestBody RaisonActifDto raisons) {
		return raisonActifService.creerRaisonActif(raisons);
	}

	@CrossOrigin(origins ="http://meritas-ui.s3-website.us-east-2.amazonaws.com")
	//@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/raison-actif-actuel")
	public List<RaisonActifDto> obtenirRaisonsActifActuel() {
		return raisonActifService.obtenirTousRaisonsActuel();
	}

	@CrossOrigin(origins ="http://meritas-ui.s3-website.us-east-2.amazonaws.com")
	//@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/raison-actif")
	public RaisonActifDto modifierRaisonActif(@RequestBody RaisonActifDto raisons) {
		return raisonActifService.creerRaisonActif(raisons);
	}
}
