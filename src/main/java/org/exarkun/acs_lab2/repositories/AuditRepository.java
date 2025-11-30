package org.exarkun.acs_lab2.repositories;

import org.exarkun.acs_lab2.entities.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Long> {

}
