package com.wipro.sk.auditservice.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.sk.auditservice.entities.Audit;
import com.wipro.sk.auditservice.services.AuditService;

import java.util.List;

@RestController
@RequestMapping("/api/audits")
@RequiredArgsConstructor
@Slf4j
public class AuditController {

    private final AuditService auditService;

    @GetMapping("/logevents")
    public ResponseEntity<List<Audit>> trackAllEvents() {
        log.info("Fetching all audit log events.");
        List<Audit> events = auditService.getAllAuditEvents();
        return ResponseEntity.ok(events);
        
   }
    
   
    
    
}
