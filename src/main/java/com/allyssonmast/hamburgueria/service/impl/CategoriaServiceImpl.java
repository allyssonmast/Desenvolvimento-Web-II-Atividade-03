package com.allyssonmast.hamburgueria.service.impl;


import com.allyssonmast.hamburgueria.dto.CategoriaRequestDTO;
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
    public CategoriaPagamento criar(CategoriaRequestDTO dto) {

        CategoriaPagamento categoria = new CategoriaPagamento();

        categoria.setNome(dto.getNome());

        return repository.save(categoria);
    }

    @Override
    public List<CategoriaPagamento> listar() {
        return repository.findAll();
    }

    @Override
    public CategoriaPagamento buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Categoria não encontrada"));
    }

    @Override
    public CategoriaPagamento atualizar(
            Long id,
            CategoriaRequestDTO dto
    ) {

        CategoriaPagamento existente = buscarPorId(id);

        existente.setNome(dto.getNome());

        return repository.save(existente);
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
