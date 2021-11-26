package ca.uqam.meritas.dto;

import java.util.List;

import ca.uqam.meritas.entity.Groupe;
import lombok.Data;

@Data
public class GroupeDto extends Groupe {
	private List<String> listeCodePermanent;
	private String sigle;
	private String noEmploye;
}
