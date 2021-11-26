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

import ca.uqam.meritas.dto.GroupeDto;
import ca.uqam.meritas.entity.Cours;
import ca.uqam.meritas.entity.Etudiant;
import ca.uqam.meritas.entity.Groupe;
import ca.uqam.meritas.entity.Professeur;
import ca.uqam.meritas.repo.CoursRepo;
import ca.uqam.meritas.repo.EtudiantRepo;
import ca.uqam.meritas.repo.GroupeRepo;
import ca.uqam.meritas.repo.ProfesseurRepo;

@Service
public class GroupeService {

	@Autowired
	private GroupeRepo groupeRepo;

	@Autowired
	private EtudiantRepo etudiantRepo;

	@Autowired
	private EtudiantService etudiantService;

	@Autowired
	private ProfesseurRepo professeurRepo;

	@Autowired
	private CoursRepo coursRepo;

	public List<GroupeDto> trouverGroupesDuCours(String sigle) {
		List<Groupe> groupes = groupeRepo.findGroupeByCoursSigle(sigle);
		return groupes.stream().map(groupe -> groupeVersDto(groupe)).collect(Collectors.toList());
	}

	public List<GroupeDto> trouverGroupesParProfesseur(String noEmploye) {
		List<Groupe> groupes = groupeRepo.findGroupeByProfesseurNoEmploye(noEmploye);
		return groupes.stream().map(groupe -> groupeVersDto(groupe)).collect(Collectors.toList());
	}

	public List<GroupeDto> obtenirTousGroupes() {
		return groupeRepo.findAll().stream().map(groupe -> groupeVersDto(groupe)).collect(Collectors.toList());
	}

	@Transactional
	public List<GroupeDto> creerGroupes(List<GroupeDto> dtos) {
		List<GroupeDto> resultat = new ArrayList<>();
		for (GroupeDto dto : dtos) {
			Groupe groupe = dtoVersGroupe(dto);
			groupe = groupeRepo.save(groupe);
			List<Etudiant> etudiants = new ArrayList<>();
			for (String codePermanent : dto.getListeCodePermanent()) {
				Optional<Etudiant> etudiantOpt = etudiantRepo.findById(codePermanent);
				if (etudiantOpt.isEmpty()) {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND,
							"Aucun étudiant pour ce groupe, donc on ne fait pas de groupe");
				}
				Etudiant etudiant = etudiantOpt.get();
				etudiant.getGroupes().add(groupe);
				etudiants.add(etudiant);
			}
			groupe.setEtudiants(etudiants);
			String noEmploye = dto.getNoEmploye();
			Optional<Professeur> profOpt = professeurRepo.findById(noEmploye);
			if (profOpt.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Aucun professeur pour ce groupe, donc on ne fait pas de groupe");
			}
			Professeur professeur = profOpt.get();
			professeur.getGroupes().add(groupe);
			groupe.setProfesseur(professeur);
			String sigle = dto.getSigle();
			Optional<Cours> coursOpt = coursRepo.findById(sigle);
			if (coursOpt.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Aucun cours pour ce groupe, donc on ne fait pas de groupe");
			}
			Cours cours = coursOpt.get();
			cours.getGroupes().add(groupe);
			groupe.setCours(cours);
			groupe = groupeRepo.save(groupe);

			resultat.add(groupeVersDto(groupe));
		}

		return resultat;
	}

	@Transactional
	public void ajouterEtudiantDansGroupes(String codePermanent, List<Integer> listeGroupes) {
		Optional<Etudiant> etudiantOpt = etudiantRepo.findById(codePermanent);
		if (etudiantOpt.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun étudiant avec ce code permanent.");
		}
		Etudiant etudiant = etudiantOpt.get();
		for (Integer groupe : listeGroupes) {
			Optional<Groupe> groupeConsulte = groupeRepo.findById(groupe);
			if (groupeConsulte.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Le groupe " + groupe + " n'existe pas.");
			}
			Groupe groupeAjout = groupeConsulte.get();
			groupeAjout.getEtudiants().add(etudiant);
			etudiant.getGroupes().add(groupeAjout);
			groupeAjout = groupeRepo.save(groupeAjout);
		}
	}

	private GroupeDto groupeVersDto(Groupe groupe) {
		GroupeDto dto = new GroupeDto();
		dto.setNumeroUnique(groupe.getNumeroUnique());
		dto.setNumero(groupe.getNumero());
		dto.setAnnee(groupe.getAnnee());
		dto.setSigle(groupe.getCours().getSigle());
		dto.setNoEmploye(groupe.getProfesseur().getNoEmploye());
		List<Etudiant> etudiantDtos = new ArrayList<Etudiant>();
		for (Etudiant etudiant : groupe.getEtudiants()) {
			etudiantDtos.add(etudiantService.etudiantVersDto(etudiant));
		}
		dto.setEtudiants(etudiantDtos);
		return dto;
	}

	private Groupe dtoVersGroupe(GroupeDto dto) {
		Groupe groupe = new Groupe();
		groupe.setNumero(dto.getNumero());
		groupe.setAnnee(dto.getAnnee());
		return groupe;
	}
}
