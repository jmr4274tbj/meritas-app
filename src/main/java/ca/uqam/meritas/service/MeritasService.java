package ca.uqam.meritas.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ca.uqam.meritas.dto.MeritasDto;
import ca.uqam.meritas.entity.Etudiant;
import ca.uqam.meritas.entity.Groupe;
import ca.uqam.meritas.entity.Meritas;
import ca.uqam.meritas.repo.EtudiantRepo;
import ca.uqam.meritas.repo.GroupeRepo;
import ca.uqam.meritas.repo.MeritasRepo;

@Service
public class MeritasService {

	@Autowired
	private MeritasRepo meritasRepo;

	@Autowired
	private EtudiantRepo etudiantRepo;

	@Autowired
	private GroupeRepo groupeRepo;

	public List<MeritasDto> obtenirTousMeritas() {
		return meritasRepo.findAll().stream().map(meritas -> meritasVersDto(meritas)).collect(Collectors.toList());
	}

	public MeritasDto creerMeritas(MeritasDto dto) {
		Meritas meritas = dtoVersMeritas(dto);
		Optional<Etudiant> etudiantOpt = etudiantRepo.findById(dto.getEtudiantCodePermanent());
		if (etudiantOpt.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Cet étudiant n'existe pas, alors on ne peut pas lui attribuer un méritas");
		}
		meritas.setRecipiendaire(etudiantOpt.get());
		Optional<Groupe> groupeOpt = groupeRepo.findById(dto.getNumeroUniqueGroupe());
		if (groupeOpt.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Aucun groupe, donc on ne peut pas attribuer de méritas");
		}
		meritas.setGroupeVise(groupeOpt.get());
		meritas = meritasRepo.save(meritas);
		return meritasVersDto(meritas);
	}

	private MeritasDto meritasVersDto(Meritas meritas) {
		MeritasDto dto = new MeritasDto();
		dto.setNumero(meritas.getNumero());
		dto.setEtape(meritas.getEtape());
		dto.setRaison(meritas.getRaison());
		dto.setNumeroUniqueGroupe(meritas.getGroupeVise().getNumeroUnique());
		dto.setEtudiantCodePermanent(meritas.getRecipiendaire().getCodePermanent());
		return dto;
	}

	private Meritas dtoVersMeritas(MeritasDto dto) {
		Meritas meritas = new Meritas();
		meritas.setEtape(dto.getEtape());
		meritas.setRaison(dto.getRaison());
		return meritas;
	}
}
