package ca.uqam.meritas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import ca.uqam.meritas.enums.Raison;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class RaisonActif {

	@Id
	private Raison raison;

	@Column(nullable = false)
	private boolean actif = false;

}
