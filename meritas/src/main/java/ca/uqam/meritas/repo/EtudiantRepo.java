package ca.uqam.meritas.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.uqam.meritas.entity.Etudiant;

@Repository
public interface EtudiantRepo extends JpaRepository<Etudiant, String> {

}
