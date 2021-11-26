package ca.uqam.meritas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Utilisateur {
	
	@Id
	private String noEmploye; 
	
	@Column(nullable = false)
	private String motDePasse;
	
	@Column(nullable = false)
	private String role;

}
