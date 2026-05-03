package com.allyssonmast.hamburgueria.service;

import com.allyssonmast.hamburgueria.dto.ClienteRequestDTO;
import com.allyssonmast.hamburgueria.model.Cliente;
import java.util.List;

public interface ClienteService {

    Cliente criar(ClienteRequestDTO cliente);

    List<Cliente> listar();

    Cliente buscarPorId(Long id);

    Cliente atualizar(Long id, ClienteRequestDTO cliente);

    void deletar(Long id);
}
