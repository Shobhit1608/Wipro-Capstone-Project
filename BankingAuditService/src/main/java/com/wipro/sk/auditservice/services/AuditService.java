package com.wipro.sk.auditservice.services;

import java.util.List;

import com.wipro.sk.auditservice.entities.Audit;

public interface AuditService {

	List<Audit> getAllAuditEvents();

	Audit saveAudit(Audit audit);
	
}
