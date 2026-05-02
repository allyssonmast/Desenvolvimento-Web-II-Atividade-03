package com.allyssonmast.hamburgueria.controller;

import com.allyssonmast.hamburgueria.dto.PagamentoRequestDTO;
import com.allyssonmast.hamburgueria.dto.PagamentoResponseDTO;
import com.allyssonmast.hamburgueria.model.TipoPagamento;
import com.allyssonmast.hamburgueria.service.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    @Qualifier("simples") /// ou avancado
    private PagamentoService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody PagamentoRequestDTO dto) {
        return ResponseEntity.ok(service.processar(dto));
    }

    @PreAuthorize(
            "hasAnyRole('ADMIN','MANAGER')"
    )
    @PutMapping("/{id}")
    public ResponseEntity<PagamentoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody PagamentoRequestDTO dto
    ) {

        return ResponseEntity.ok(
                service.atualizar(id, dto)
        );
    }

    @PreAuthorize(
            "hasAnyRole('ADMIN','MANAGER','ATTENDANT')"
    )
    @GetMapping
    public ResponseEntity<List<PagamentoResponseDTO>> listar() {

        return ResponseEntity.ok(
                service.listar()
        );
    }

    @PreAuthorize(
            "hasAnyRole('ADMIN','MANAGER','ATTENDANT')"
    )
    @GetMapping("/{id}")
    public ResponseEntity<PagamentoResponseDTO> buscar(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                service.buscarPorId(id)
        );
    }

    @PreAuthorize(
            "hasAnyRole('ADMIN','MANAGER','ATTENDANT')"
    )
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<PagamentoResponseDTO>>
    buscarPorTipo(
            @PathVariable TipoPagamento tipo
    ) {

        return ResponseEntity.ok(
                service.buscarPorTipo(tipo)
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id
    ) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
