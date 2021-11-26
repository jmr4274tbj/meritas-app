package ca.uqam.meritas.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.uqam.meritas.entity.Meritas;

@Repository
public interface MeritasRepo extends JpaRepository<Meritas, Integer> {

}
