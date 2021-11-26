package ca.uqam.meritas.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Professeur {

	@Id
	private String noEmploye;

	@Column(nullable = false)
	private String prenom;

	@Column(nullable = false)
	private String nom;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "professeur")
	private List<Groupe> groupes;

	@JsonIgnore
	@ManyToOne
	private Utilisateur utilisateur;

}
