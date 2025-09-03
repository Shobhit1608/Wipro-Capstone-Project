package com.wipro.sk.auditservice.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.sk.auditservice.entities.Audit;
import com.wipro.sk.auditservice.repositories.AuditRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

	 private final AuditRepository auditRepository;
	
	 @Override
	public List<Audit> getAllAuditEvents() {
		return auditRepository.findAll();
	}
	
	 @Override
	    public Audit saveAudit(Audit audit) {
	        audit.setAuditedAt(LocalDateTime.now());
	        return auditRepository.save(audit);
	    }
}
