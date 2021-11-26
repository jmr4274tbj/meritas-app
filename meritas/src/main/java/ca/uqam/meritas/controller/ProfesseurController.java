package ca.uqam.meritas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ca.uqam.meritas.dto.GroupeDto;
import ca.uqam.meritas.dto.ProfesseurDto;
import ca.uqam.meritas.service.GroupeService;
import ca.uqam.meritas.service.ProfesseurService;

@RestController
public class ProfesseurController {

	@Autowired
	private ProfesseurService professeurService;
	@Autowired
	private GroupeService groupeService;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/professeurs")
	public List<ProfesseurDto> obtenirProfesseurs() {
		return professeurService.obtenirTousProfesseurs();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/professeurs")
	@ResponseStatus(HttpStatus.CREATED)
	public List<ProfesseurDto> creerProfesseurs(@RequestBody List<ProfesseurDto> professeurs) {
		return professeurService.creerProfesseurs(professeurs);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/professeurs/{noEmploye}/groupes")
	public List<GroupeDto> trouverGroupesParProfesseur(@PathVariable String noEmploye) {
		return groupeService.trouverGroupesParProfesseur(noEmploye);
	}
}
