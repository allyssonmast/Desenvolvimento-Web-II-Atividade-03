package com.allyssonmast.hamburgueria.strategy;

import com.allyssonmast.hamburgueria.model.Pagamento;
import com.allyssonmast.hamburgueria.model.StatusPagamento;

public interface ProcessadorPagamentoStrategy {
    StatusPagamento processar(Pagamento pagamento);
}
