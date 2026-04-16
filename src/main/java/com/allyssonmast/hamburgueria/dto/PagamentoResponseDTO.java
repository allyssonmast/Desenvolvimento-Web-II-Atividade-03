package com.allyssonmast.hamburgueria.dto;

import com.allyssonmast.hamburgueria.model.StatusPagamento;
import com.allyssonmast.hamburgueria.model.TipoPagamento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoResponseDTO {
    private Long id;
    private double valor;
    private TipoPagamento tipo;
    private StatusPagamento status;
}
