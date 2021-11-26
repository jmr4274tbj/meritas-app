package ca.uqam.meritas.dto;

import java.util.List;

import ca.uqam.meritas.entity.Professeur;
import lombok.Data;

@Data
public class ProfesseurDto extends Professeur {
	private List<Integer> listeGroupe;
	private String motDePasse;
}
