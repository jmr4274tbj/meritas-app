package ca.uqam.meritas.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.uqam.meritas.dto.UtilisateurDto;
import ca.uqam.meritas.entity.Utilisateur;
import ca.uqam.meritas.repo.UtilisateurRepo;

@Service
public class UtilisateurService {

	@Autowired
	private UtilisateurRepo utilisateurRepo;

	public List<UtilisateurDto> obtenirTousUtilisateurs() {
		return utilisateurRepo.findAll().stream().map(utilisateur -> utilisateurVersDto(utilisateur))
				.collect(Collectors.toList());
	}

	public UtilisateurDto creerUtilisateur(UtilisateurDto utilisateurDto) {
		Utilisateur utilisateur = dtoVersUtilisateur(utilisateurDto);
		utilisateur = utilisateurRepo.save(utilisateur);
		return utilisateurVersDto(utilisateur);
	}

	@Transactional
	@EventListener(ApplicationReadyEvent.class)
	public void insererUtilisateurInitial() {
		Utilisateur admin = new Utilisateur();
		admin.setNoEmploye("admin001");
		admin.setMotDePasse("ADminiSTRATION");
		admin.setRole("administrateur");
		Utilisateur secretaire = new Utilisateur();
		secretaire.setNoEmploye("secretariat01");
		secretaire.setMotDePasse("secretTAIRE");
		secretaire.setRole("secretaire");
		Optional<Utilisateur> utilisateurOpt = utilisateurRepo.findById(admin.getNoEmploye());
		if (!utilisateurOpt.isPresent()) {
			utilisateurRepo.save(admin);
		}
		utilisateurOpt = utilisateurRepo.findById(secretaire.getNoEmploye());
		if (!utilisateurOpt.isPresent()) {
			utilisateurRepo.save(secretaire);
		}
	}

	public UtilisateurDto validerUtilisateur(UtilisateurDto dto) {
		String noEmploye = dto.getNoEmploye();
		Optional<Utilisateur> utilisateurOpt = utilisateurRepo.findById(noEmploye);
		if (utilisateurOpt.isEmpty()) {
			return null;
		}
		Utilisateur utilisateur = utilisateurOpt.get();
		if (!utilisateur.getMotDePasse().equals(dto.getMotDePasse())) {
			return null;
		}
		return utilisateurVersDto(utilisateur);
	}

	private UtilisateurDto utilisateurVersDto(Utilisateur utilisateur) {
		UtilisateurDto dto = new UtilisateurDto();
		dto.setNoEmploye(utilisateur.getNoEmploye());
		dto.setMotDePasse(utilisateur.getMotDePasse());
		dto.setRole(utilisateur.getRole());
		return dto;
	}

	private Utilisateur dtoVersUtilisateur(UtilisateurDto dto) {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNoEmploye(dto.getNoEmploye());
		utilisateur.setMotDePasse(dto.getMotDePasse());
		utilisateur.setRole(dto.getRole());
		return utilisateur;
	}

}
