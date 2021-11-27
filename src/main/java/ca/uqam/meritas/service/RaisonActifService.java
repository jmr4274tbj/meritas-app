package ca.uqam.meritas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.uqam.meritas.dto.RaisonActifDto;
import ca.uqam.meritas.entity.RaisonActif;
import ca.uqam.meritas.enums.Raison;
import ca.uqam.meritas.repo.RaisonActifRepo;

@Service
public class RaisonActifService {

	@Autowired
	private RaisonActifRepo raisonActifRepo;

	public List<RaisonActifDto> obtenirTousRaisons() {
		return raisonActifRepo.findAll().stream().map(raisons -> raisonActifVersDto(raisons))
				.collect(Collectors.toList());
	}

	public RaisonActifDto creerRaisonActif(RaisonActifDto raisonsDto) {
		RaisonActif raisonActif = dtoVersRaisonActif(raisonsDto);
		raisonActif = raisonActifRepo.save(raisonActif);
		return raisonActifVersDto(raisonActif);
	}

	@Transactional
	@EventListener(ApplicationReadyEvent.class)
	public void insererRaisonActifInitiale() {
		for (Raison raison : Raison.values()) {
			Optional<RaisonActif> ra = raisonActifRepo.findById(raison);
			if (ra.isEmpty()) {
				RaisonActif raisonActif = new RaisonActif();
				raisonActif.setRaison(raison);
				raisonActifRepo.save(raisonActif);
			}
		}
	}

	public List<RaisonActifDto> obtenirTousRaisonsActuel() {
		List<RaisonActifDto> listeComplete = raisonActifRepo.findAll().stream()
				.map(raisons -> raisonActifVersDto(raisons)).collect(Collectors.toList());
		List<RaisonActifDto> listeActive = new ArrayList<>();
		for (RaisonActifDto radto : listeComplete) {
			if (radto.isActif()) {
				listeActive.add(radto);
			}
		}
		return listeActive;
	}

	private RaisonActifDto raisonActifVersDto(RaisonActif raisons) {
		RaisonActifDto dto = new RaisonActifDto();
		dto.setRaison(raisons.getRaison());
		dto.setActif(raisons.isActif());
		return dto;
	}

	private RaisonActif dtoVersRaisonActif(RaisonActifDto dto) {
		RaisonActif raisons = new RaisonActif();
		raisons.setRaison(dto.getRaison());
		raisons.setActif(dto.isActif());
		return raisons;
	}
}
