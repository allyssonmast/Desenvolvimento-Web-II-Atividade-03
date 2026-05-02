package com.allyssonmast.hamburgueria.controller;

import com.allyssonmast.hamburgueria.model.CategoriaPagamento;
import com.allyssonmast.hamburgueria.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody CategoriaPagamento categoria) {
        return ResponseEntity.ok(service.criar(categoria));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','ATTENDANT')")
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','ATTENDANT')")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','ATTENDANT')")
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody CategoriaPagamento categoria) {
        return ResponseEntity.ok(service.atualizar(id, categoria));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
