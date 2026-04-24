package com.allyssonmast.hamburgueria.service;

import com.allyssonmast.hamburgueria.model.CategoriaPagamento;
import java.util.List;

public interface CategoriaService {

    CategoriaPagamento criar(CategoriaPagamento categoria);

    List<CategoriaPagamento> listar();

    CategoriaPagamento buscarPorId(Long id);

    CategoriaPagamento atualizar(Long id, CategoriaPagamento categoria);

    void deletar(Long id);
}
