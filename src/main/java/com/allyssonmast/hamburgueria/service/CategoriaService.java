package com.allyssonmast.hamburgueria.service;

import com.allyssonmast.hamburgueria.dto.CategoriaRequestDTO;
import com.allyssonmast.hamburgueria.dto.CategoriaResponseDTO;

import java.util.List;

public interface CategoriaService {

    CategoriaResponseDTO criar(CategoriaRequestDTO categoria);

    List<CategoriaResponseDTO> listar();

    CategoriaResponseDTO buscarPorId(Long id);

    CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO categoria);

    void deletar(Long id);
}