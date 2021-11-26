package ca.uqam.meritas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.uqam.meritas.dto.ProfesseurDto;
import ca.uqam.meritas.dto.UtilisateurDto;
import ca.uqam.meritas.entity.Professeur;
import ca.uqam.meritas.repo.ProfesseurRepo;

@Service
public class ProfesseurService {

	@Autowired
	private ProfesseurRepo professeurRepo;

	@Autowired
	private UtilisateurService utilisateurService;

	public List<ProfesseurDto> obtenirTousProfesseurs() {
		return professeurRepo.findAll().stream().map(professeur -> professeurVersDto(professeur))
				.collect(Collectors.toList());
	}

	@Transactional
	public List<ProfesseurDto> creerProfesseurs(List<ProfesseurDto> dtos) {
		List<ProfesseurDto> resultat = new ArrayList<>();
		for (ProfesseurDto dto : dtos) {
			Professeur professeur = dtoVersProfesseur(dto);
			String id = professeur.getNoEmploye();
			Optional<Professeur> profOpt = professeurRepo.findById(id);
			if (!profOpt.isPresent()) {
				professeur = professeurRepo.save(professeur);
				UtilisateurDto utilisateur = new UtilisateurDto();
				utilisateur.setNoEmploye(id);
				utilisateur.setMotDePasse(dto.getMotDePasse());
				utilisateur.setRole("professeur");
				utilisateurService.creerUtilisateur(utilisateur);
			} else {
				professeur = profOpt.get();
			}
			resultat.add(professeurVersDto(professeur));
		}
		return resultat;
	}

	private ProfesseurDto professeurVersDto(Professeur professeur) {
		ProfesseurDto dto = new ProfesseurDto();
		dto.setNoEmploye(professeur.getNoEmploye());
		dto.setPrenom(professeur.getPrenom());
		dto.setNom(professeur.getNom());
		return dto;
	}

	private Professeur dtoVersProfesseur(ProfesseurDto dto) {
		Professeur professeur = new Professeur();
		professeur.setNoEmploye(dto.getNoEmploye());
		professeur.setPrenom(dto.getPrenom());
		professeur.setNom(dto.getNom());
		return professeur;
	}

}
