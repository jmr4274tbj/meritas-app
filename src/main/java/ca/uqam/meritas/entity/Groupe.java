package ca.uqam.meritas.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Groupe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer numeroUnique;

	@Column(nullable = false)
	private Integer numero;

	@JsonIgnore
	@ManyToOne
	private Professeur professeur;

	@JsonIgnore
	@ManyToOne
	private Cours cours;

	@ManyToMany
	@JoinTable(name = "groupe_etudiant", joinColumns = @JoinColumn(name = "groupe_numero_unique"), inverseJoinColumns = @JoinColumn(name = "etudiant_code_permanent"))
	private List<Etudiant> etudiants;

	@Column(nullable = false)
	private Integer annee;
}
