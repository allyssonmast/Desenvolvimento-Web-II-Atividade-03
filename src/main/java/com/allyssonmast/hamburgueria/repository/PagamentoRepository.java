package com.allyssonmast.hamburgueria.repository;

import com.allyssonmast.hamburgueria.model.Pagamento;
import com.allyssonmast.hamburgueria.model.TipoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

    List<Pagamento> findByTipo(TipoPagamento tipo);
}
