package com.allyssonmast.hamburgueria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.allyssonmast.hamburgueria.dto.AuditLogResponseDTO;
import com.allyssonmast.hamburgueria.service.AuditService;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/auditoria")
public class AuditController {

    @Autowired
    private AuditService service;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<AuditLogResponseDTO>> listar() {

        List<AuditLogResponseDTO> logs = service.listar();

        return ResponseEntity.ok(logs);
    }
}
