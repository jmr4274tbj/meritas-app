package ca.uqam.meritas.dto;

import ca.uqam.meritas.entity.Meritas;
import lombok.Data;

@Data
public class MeritasDto extends Meritas {
	private String etudiantCodePermanent;
	private Integer numeroUniqueGroupe;
}
