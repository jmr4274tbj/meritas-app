package ca.uqam.meritas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ca.uqam.meritas.enums.Raison;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Meritas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer numero;

	@Column(nullable = false)
	private Integer etape;

	@JsonIgnore
	@ManyToOne
	private Etudiant recipiendaire;

	@JsonIgnore
	@ManyToOne
	private Groupe groupeVise;

	@Column(nullable = false)
	private Raison raison;

}
