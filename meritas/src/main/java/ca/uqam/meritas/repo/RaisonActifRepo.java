package ca.uqam.meritas.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.uqam.meritas.entity.RaisonActif;
import ca.uqam.meritas.enums.Raison;

public interface RaisonActifRepo extends JpaRepository<RaisonActif, Raison> {

}
