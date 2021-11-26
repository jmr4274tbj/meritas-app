package ca.uqam.meritas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ca.uqam.meritas.dto.MeritasDto;
import ca.uqam.meritas.service.MeritasService;

@RestController
public class MeritasController {

	@Autowired
	private MeritasService meritasService;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/meritas")
	public List<MeritasDto> obtenirMeritas() {
		return meritasService.obtenirTousMeritas();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/meritas")
	@ResponseStatus(HttpStatus.CREATED)
	public MeritasDto creerMeritas(@RequestBody MeritasDto meritas) {
		return meritasService.creerMeritas(meritas);
	}
}
