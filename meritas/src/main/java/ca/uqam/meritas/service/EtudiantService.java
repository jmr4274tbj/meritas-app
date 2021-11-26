package ca.uqam.meritas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import ca.uqam.meritas.dto.EtudiantDto;
import ca.uqam.meritas.entity.Etudiant;
import ca.uqam.meritas.repo.EtudiantRepo;

@Service
public class EtudiantService {

	@Autowired
	private EtudiantRepo etudiantRepo;

	public List<EtudiantDto> obtenirTousEtudiants() {
		return etudiantRepo.findAll().stream().map(etudiant -> etudiantVersDto(etudiant)).collect(Collectors.toList());
	}

	public EtudiantDto changerEtudiantActifEnInactif(EtudiantDto dto) {
		Etudiant etudiant = dtoVersEtudiant(dto);
		String id = etudiant.getCodePermanent();
		etudiant.setActif(false);
		Optional<Etudiant> etudiantOpt = etudiantRepo.findById(id);
		if (etudiantOpt.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun étudiant avec ce code permanent");
		} else {
			etudiant.setPrenom(etudiantOpt.get().getPrenom());
			etudiant.setNom(etudiantOpt.get().getNom());
			etudiant = etudiantRepo.save(etudiant);
		}

		return etudiantVersDto(etudiant);
	}

	@Transactional
	public List<EtudiantDto> creerEtudiants(List<EtudiantDto> dtos) {
		List<EtudiantDto> resultat = new ArrayList<EtudiantDto>();
		for (EtudiantDto dto : dtos) {
			Etudiant etudiant = dtoVersEtudiant(dto);
			String id = etudiant.getCodePermanent();
			Optional<Etudiant> etudiantOpt = etudiantRepo.findById(id);
			if (etudiantOpt.isEmpty()) {
				etudiant = etudiantRepo.save(etudiant);
			} else {
				etudiant = etudiantOpt.get();
			}
			resultat.add(etudiantVersDto(etudiant));
		}
		return resultat;
	}

	protected EtudiantDto etudiantVersDto(Etudiant etudiant) {
		EtudiantDto dto = new EtudiantDto();
		dto.setCodePermanent(etudiant.getCodePermanent());
		dto.setPrenom(etudiant.getPrenom());
		dto.setNom(etudiant.getNom());
		dto.setActif(etudiant.isActif());
		return dto;
	}

	private Etudiant dtoVersEtudiant(EtudiantDto dto) {
		Etudiant etudiant = new Etudiant();
		etudiant.setCodePermanent(dto.getCodePermanent());
		etudiant.setPrenom(dto.getPrenom());
		etudiant.setNom(dto.getNom());
		etudiant.setActif(dto.isActif());
		return etudiant;
	}
}
