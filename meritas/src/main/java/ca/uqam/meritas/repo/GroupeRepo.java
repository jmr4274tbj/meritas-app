package ca.uqam.meritas.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.uqam.meritas.entity.Groupe;

@Repository
public interface GroupeRepo extends JpaRepository<Groupe, Integer> {

	public List<Groupe> findGroupeByCoursSigle(String sigle);

	public List<Groupe> findGroupeByProfesseurNoEmploye(String noEmploye);

}
