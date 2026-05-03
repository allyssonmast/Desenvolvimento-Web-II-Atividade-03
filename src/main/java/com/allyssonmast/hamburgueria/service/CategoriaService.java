package com.allyssonmast.hamburgueria.service;

import com.allyssonmast.hamburgueria.dto.CategoriaRequestDTO;
import com.allyssonmast.hamburgueria.model.CategoriaPagamento;
import java.util.List;

public interface CategoriaService {

    CategoriaPagamento criar(CategoriaRequestDTO categoria);

    List<CategoriaPagamento> listar();

    CategoriaPagamento buscarPorId(Long id);

    CategoriaPagamento atualizar(Long id, CategoriaRequestDTO categoria);

    void deletar(Long id);
}
