package ca.uqam.meritas.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.uqam.meritas.entity.Cours;

@Repository
public interface CoursRepo extends JpaRepository<Cours, String> {

}
