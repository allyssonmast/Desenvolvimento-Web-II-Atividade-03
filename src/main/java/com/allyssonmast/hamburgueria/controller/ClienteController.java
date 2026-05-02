package com.allyssonmast.hamburgueria.controller;

import com.allyssonmast.hamburgueria.model.Cliente;
import com.allyssonmast.hamburgueria.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PreAuthorize(
            "hasAnyRole('ADMIN','MANAGER')"
    )
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(service.criar(cliente));
    }

    @PreAuthorize(
            "hasAnyRole('ADMIN','MANAGER','ATTENDANT')"
    )
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PreAuthorize(
            "hasAnyRole('ADMIN','MANAGER','ATTENDANT')"
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PreAuthorize(
            "hasAnyRole('ADMIN','MANAGER')"
    )
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        return ResponseEntity.ok(service.atualizar(id, cliente));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
