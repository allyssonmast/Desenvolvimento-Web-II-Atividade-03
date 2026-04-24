package com.allyssonmast.hamburgueria.repository.primary;

import com.allyssonmast.hamburgueria.model.CategoriaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaPagamento, Long> { }
