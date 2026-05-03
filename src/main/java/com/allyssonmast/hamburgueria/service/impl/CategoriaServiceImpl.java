package com.allyssonmast.hamburgueria.service.impl;

import com.allyssonmast.hamburgueria.dto.CategoriaRequestDTO;
import com.allyssonmast.hamburgueria.dto.CategoriaResponseDTO;
import com.allyssonmast.hamburgueria.exception.NotFoundException;
import com.allyssonmast.hamburgueria.model.CategoriaPagamento;
import com.allyssonmast.hamburgueria.repository.primary.CategoriaRepository;
import com.allyssonmast.hamburgueria.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Override
    public CategoriaResponseDTO criar(CategoriaRequestDTO dto) {

        CategoriaPagamento categoria = new CategoriaPagamento();

        categoria.setNome(dto.getNome());

        CategoriaPagamento salva = repository.save(categoria);

        return toResponseDTO(salva);
    }

    @Override
    public List<CategoriaResponseDTO> listar() {

        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    public CategoriaResponseDTO buscarPorId(Long id) {

        CategoriaPagamento categoria = buscarEntidadePorId(id);

        return toResponseDTO(categoria);
    }

    @Override
    public CategoriaResponseDTO atualizar(
            Long id,
            CategoriaRequestDTO dto
    ) {

        CategoriaPagamento existente = buscarEntidadePorId(id);

        existente.setNome(dto.getNome());

        CategoriaPagamento atualizada = repository.save(existente);

        return toResponseDTO(atualizada);
    }

    @Override
    public void deletar(Long id) {

        CategoriaPagamento categoria = buscarEntidadePorId(id);

        repository.delete(categoria);
    }

    private CategoriaPagamento buscarEntidadePorId(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Categoria não encontrada")
                );
    }

    private CategoriaResponseDTO toResponseDTO(
            CategoriaPagamento categoria
    ) {

        CategoriaResponseDTO dto = new CategoriaResponseDTO();

        dto.setId(categoria.getId());
        dto.setNome(categoria.getNome());

        return dto;
    }
}