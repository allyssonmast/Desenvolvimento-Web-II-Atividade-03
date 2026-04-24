package com.allyssonmast.hamburgueria.controller;

import com.allyssonmast.hamburgueria.model.Cliente;
import com.allyssonmast.hamburgueria.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<?> criar(
            @RequestBody Cliente cliente
    ) {
        return ResponseEntity.ok(
                service.criar(cliente)
        );
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(
                service.listar()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                service.buscarPorId(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestBody Cliente cliente
    ) {
        return ResponseEntity.ok(
                service.atualizar(id, cliente)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(
            @PathVariable Long id
    ) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
