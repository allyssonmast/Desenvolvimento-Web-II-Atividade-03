package com.allyssonmast.hamburgueria.controller;

import com.allyssonmast.hamburgueria.dto.CategoriaRequestDTO;
import com.allyssonmast.hamburgueria.dto.CategoriaResponseDTO;
import com.allyssonmast.hamburgueria.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
@RequestMapping("/categorias")
@Tag(
        name = "Categorias",
        description = "Endpoints para gerenciamento de categorias de pagamento"
)
@SecurityRequirement(name = "bearerAuth")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @Operation(
            summary = "Criar categoria",
            description = "Cria uma nova categoria de pagamento. Apenas ADMIN pode acessar."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Categoria criada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = CategoriaResponseDTO.class
                            )
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
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> criar(

            @Valid
            @RequestBody
            CategoriaRequestDTO categoria
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.criar(categoria));
    }

    @Operation(
            summary = "Listar categorias",
            description = "Retorna todas as categorias cadastradas."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista retornada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(
                                            implementation =
                                                    CategoriaResponseDTO.class
                                    )
                            )
                    )
            )
    })
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','ATTENDANT')")
    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listar() {

        return ResponseEntity.ok(service.listar());
    }

    @Operation(
            summary = "Buscar categoria por ID",
            description = "Retorna uma categoria específica pelo ID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Categoria encontrada"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Categoria não encontrada"
            )
    })
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','ATTENDANT')")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> buscar(

            @Parameter(
                    description = "ID da categoria",
                    example = "1"
            )
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(
            summary = "Atualizar categoria",
            description = "Atualiza os dados de uma categoria existente."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Categoria atualizada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Categoria não encontrada"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos"
            )
    })
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','ATTENDANT')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizar(

            @Parameter(
                    description = "ID da categoria",
                    example = "1"
            )
            @PathVariable Long id,

            @Valid
            @RequestBody
            CategoriaRequestDTO dto
    ) {

        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @Operation(
            summary = "Remover categoria",
            description = "Remove uma categoria pelo ID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Categoria removida com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Categoria não encontrada"
            )
    })
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(

            @Parameter(
                    description = "ID da categoria",
                    example = "1"
            )
            @PathVariable Long id
    ) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}