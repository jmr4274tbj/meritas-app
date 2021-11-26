package ca.uqam.meritas.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Cours {
	
	@Id
	private String sigle;
	
	@Column(nullable = false)
	private String nomMatiere;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cours")
	private List<Groupe> groupes;

}
