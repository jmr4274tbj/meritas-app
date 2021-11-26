package ca.uqam.meritas.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Etudiant {

	@Id
	private String codePermanent;

	@Column(nullable = false)
	private String prenom;

	@Column(nullable = false)
	private String nom;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "etudiants")
	private List<Groupe> groupes;

	@Column(nullable = false)
	private boolean actif = true;
}
