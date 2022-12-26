package edu.stateuniversityoftelecommunications.project.repository;

import edu.stateuniversityoftelecommunications.project.domain.ShipmentEndedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<ShipmentEndedEntity, Long> {
}
