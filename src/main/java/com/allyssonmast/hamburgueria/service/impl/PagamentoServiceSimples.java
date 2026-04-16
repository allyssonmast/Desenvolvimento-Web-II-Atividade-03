package com.allyssonmast.hamburgueria.service.impl;

import com.allyssonmast.hamburgueria.dto.PagamentoRequestDTO;
import com.allyssonmast.hamburgueria.dto.PagamentoResponseDTO;
import com.allyssonmast.hamburgueria.exception.PaymentException;
import com.allyssonmast.hamburgueria.model.Pagamento;
import com.allyssonmast.hamburgueria.model.StatusPagamento;
import com.allyssonmast.hamburgueria.model.TipoPagamento;
import com.allyssonmast.hamburgueria.repository.PagamentoRepository;
import com.allyssonmast.hamburgueria.service.PagamentoService;
import com.allyssonmast.hamburgueria.strategy.factory.PagamentoStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("simples")
public class PagamentoServiceSimples implements PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private PagamentoStrategyFactory factory;

    public PagamentoResponseDTO processar(PagamentoRequestDTO dto) {

        if (dto.getValor() <= 0) {
            throw new PaymentException("Valor inválido");
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setValor(dto.getValor());
        pagamento.setTipo(dto.getTipo());
        pagamento.setDescricao(dto.getDescricao());

        StatusPagamento status = factory
                .getStrategy(dto.getTipo())
                .processar(pagamento);

        pagamento.setStatus(status);

        Pagamento salvo = repository.save(pagamento);

        return toDTO(salvo);
    }

    private PagamentoResponseDTO toDTO(Pagamento p) {
        PagamentoResponseDTO dto = new PagamentoResponseDTO();
        dto.setId(p.getId());
        dto.setValor(p.getValor());
        dto.setTipo(p.getTipo());
        dto.setStatus(p.getStatus());
        return dto;
    }

    public List<PagamentoResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public PagamentoResponseDTO buscarPorId(Long id) {
        Pagamento p = repository.findById(id)
                .orElseThrow(() -> new PaymentException("Não encontrado"));
        return toDTO(p);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<PagamentoResponseDTO> buscarPorTipo(TipoPagamento tipo) {
        return repository.findByTipo(tipo)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public PagamentoResponseDTO atualizar(Long id, PagamentoRequestDTO dto) {
        Pagamento pago = repository.findById(id)
                .orElseThrow(() -> new PaymentException("Não encontrado"));

        if (dto.getValor() <= 0) {
            throw new PaymentException("Valor inválido");
        }
        pago.setValor(dto.getValor());

        if (dto.getTipo() != null) {
            pago.setTipo(dto.getTipo());
        }

        pago.setDescricao(dto.getDescricao());

        StatusPagamento status = factory
                .getStrategy(pago.getTipo())
                .processar(pago);
        pago.setStatus(status);

        Pagamento salvo = repository.save(pago);
        return toDTO(salvo);
    }
}
