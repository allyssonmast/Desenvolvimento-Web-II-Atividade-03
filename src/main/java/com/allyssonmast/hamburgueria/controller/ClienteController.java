package com.allyssonmast.hamburgueria.controller;

import com.allyssonmast.hamburgueria.dto.ClienteRequestDTO;
import com.allyssonmast.hamburgueria.model.Cliente;
import com.allyssonmast.hamburgueria.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Tag(
        name = "Clientes",
        description = "Endpoints para gerenciamento de clientes"
)
@SecurityRequirement(name = "bearerAuth")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @Operation(
            summary = "Criar cliente",
            description = "Cria um novo cliente. Apenas ADMIN e MANAGER podem acessar."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Cliente criado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Não autenticado"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Acesso negado"
            )
    })
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PostMapping
    public ResponseEntity<Cliente> criar(

            @Valid
            @RequestBody
            ClienteRequestDTO dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.criar(dto));
    }

    @Operation(
            summary = "Listar clientes",
            description = "Retorna todos os clientes cadastrados."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista retornada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Não autenticado"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Acesso negado"
            )
    })
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','ATTENDANT')")
    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {

        return ResponseEntity.ok(service.listar());
    }

    @Operation(
            summary = "Buscar cliente por ID",
            description = "Retorna um cliente específico pelo ID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cliente encontrado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente não encontrado"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Não autenticado"
            )
    })
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','ATTENDANT')")
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscar(

            @Parameter(
                    description = "ID do cliente",
                    example = "1"
            )
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(
            summary = "Atualizar cliente",
            description = "Atualiza os dados de um cliente existente."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cliente atualizado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente não encontrado"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Não autenticado"
            )
    })
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(

            @Parameter(
                    description = "ID do cliente",
                    example = "1"
            )
            @PathVariable Long id,

            @Valid
            @RequestBody
            ClienteRequestDTO cliente
    ) {

        return ResponseEntity.ok(service.atualizar(id, cliente));
    }

    @Operation(
            summary = "Remover cliente",
            description = "Remove um cliente pelo ID. Apenas ADMIN pode acessar."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Cliente removido com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Cliente não encontrado"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Não autenticado"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Acesso negado"
            )
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(

            @Parameter(
                    description = "ID do cliente",
                    example = "1"
            )
            @PathVariable Long id
    ) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}