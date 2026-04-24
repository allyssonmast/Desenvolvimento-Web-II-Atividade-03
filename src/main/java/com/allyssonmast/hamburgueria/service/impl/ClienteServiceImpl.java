package com.allyssonmast.hamburgueria.service.impl;

import com.allyssonmast.hamburgueria.model.Cliente;
import com.allyssonmast.hamburgueria.repository.primary.ClienteRepository;
import com.allyssonmast.hamburgueria.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Override
    public Cliente criar(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public List<Cliente> listar() {
        return repository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Cliente não encontrado"));
    }

    @Override
    public Cliente atualizar(Long id, Cliente cliente) {

        Cliente existente = buscarPorId(id);

        existente.setNome(cliente.getNome());
        existente.setEmail(cliente.getEmail());

        return repository.save(existente);
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
