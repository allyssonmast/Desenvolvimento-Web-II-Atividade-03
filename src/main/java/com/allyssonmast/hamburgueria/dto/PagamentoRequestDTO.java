package com.allyssonmast.hamburgueria.dto;

import com.allyssonmast.hamburgueria.model.TipoPagamento;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoRequestDTO {

    @Min(1)
    private double valor;

    @NotNull
    private TipoPagamento tipo;

    @NotBlank
    private String descricao;
}
