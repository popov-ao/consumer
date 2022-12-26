package edu.stateuniversityoftelecommunications.project.repository;

import edu.stateuniversityoftelecommunications.project.domain.CartonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartonRepository extends JpaRepository<CartonEntity, Long> {
}
