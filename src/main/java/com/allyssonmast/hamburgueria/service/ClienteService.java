package com.allyssonmast.hamburgueria.service;

import com.allyssonmast.hamburgueria.model.Cliente;
import java.util.List;

public interface ClienteService {

    Cliente criar(Cliente cliente);

    List<Cliente> listar();

    Cliente buscarPorId(Long id);

    Cliente atualizar(Long id, Cliente cliente);

    void deletar(Long id);
}
