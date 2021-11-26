package ca.uqam.meritas.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.uqam.meritas.entity.Utilisateur;

@Repository
public interface UtilisateurRepo extends JpaRepository<Utilisateur, String> {

}
