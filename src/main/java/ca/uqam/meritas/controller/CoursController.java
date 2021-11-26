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

import ca.uqam.meritas.dto.CoursDto;
import ca.uqam.meritas.dto.GroupeDto;
import ca.uqam.meritas.service.CoursService;
import ca.uqam.meritas.service.GroupeService;

@RestController
public class CoursController {

	@Autowired
	private CoursService coursService;
	@Autowired
	private GroupeService groupeService;

	@CrossOrigin(origins ="http://meritas-ui.s3-website.us-east-2.amazonaws.com")
	//@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/cours")
	public List<CoursDto> obtenirCours() {
		return coursService.obtenirTousCours();
	}

	@CrossOrigin(origins ="http://meritas-ui.s3-website.us-east-2.amazonaws.com")
	//@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/cours")
	@ResponseStatus(HttpStatus.CREATED)
	public List<CoursDto> creerCours(@RequestBody List<CoursDto> cours) {
		return coursService.creerCours(cours);
	}

	@CrossOrigin(origins ="http://meritas-ui.s3-website.us-east-2.amazonaws.com")
	//@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/cours/{sigle}/groupes")
	public List<GroupeDto> trouverGroupesDuCours(@PathVariable String sigle) {
		return groupeService.trouverGroupesDuCours(sigle);
	}

}
