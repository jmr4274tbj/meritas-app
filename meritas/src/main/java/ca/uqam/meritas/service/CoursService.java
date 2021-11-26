package ca.uqam.meritas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.uqam.meritas.dto.CoursDto;
import ca.uqam.meritas.entity.Cours;
import ca.uqam.meritas.repo.CoursRepo;

@Service
public class CoursService {

	@Autowired
	private CoursRepo coursRepo;

	public List<CoursDto> obtenirTousCours() {
		return coursRepo.findAll().stream().map(cours -> coursVersDto(cours)).collect(Collectors.toList());
	}

	@Transactional
	public List<CoursDto> creerCours(List<CoursDto> dtos) {
		List<CoursDto> resultat = new ArrayList<>();
		for (CoursDto dto : dtos) {
			Cours cours = dtoVersCours(dto);
			String id = cours.getSigle();
			Optional<Cours> coursOpt = coursRepo.findById(id);
			if (coursOpt.isEmpty()) {
				cours = coursRepo.save(cours);
			} else {
				cours = coursOpt.get();
			}
			resultat.add(coursVersDto(cours));
		}
		return resultat;
	}

	private CoursDto coursVersDto(Cours cours) {
		CoursDto dto = new CoursDto();
		dto.setSigle(cours.getSigle());
		dto.setNomMatiere(cours.getNomMatiere());
		return dto;
	}

	private Cours dtoVersCours(CoursDto dto) {
		Cours cours = new Cours();
		cours.setSigle(dto.getSigle());
		cours.setNomMatiere(dto.getNomMatiere());
		return cours;
	}
}
