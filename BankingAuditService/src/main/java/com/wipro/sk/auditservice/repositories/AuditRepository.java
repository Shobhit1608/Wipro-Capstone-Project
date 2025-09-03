package com.wipro.sk.auditservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.sk.auditservice.entities.Audit;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Long> {

}
