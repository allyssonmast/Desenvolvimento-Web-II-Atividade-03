package com.allyssonmast.hamburgueria.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double valor;

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipo;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "pagamento_categoria",
            joinColumns = @JoinColumn(name = "pagamento_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<CategoriaPagamento> categorias;
}
