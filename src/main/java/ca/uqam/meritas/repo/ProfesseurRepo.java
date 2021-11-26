package ca.uqam.meritas.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.uqam.meritas.entity.Professeur;

@Repository
public interface ProfesseurRepo extends JpaRepository<Professeur, String> {

}
