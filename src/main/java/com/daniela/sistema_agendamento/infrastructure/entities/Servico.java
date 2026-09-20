package com.daniela.sistema_agendamento.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "servico")
@Entity

public class Servico<string> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nome", nullable = false)
    private string nome;

    @Column(name = "nome", nullable = false)
    private BigDecimal valor;

    @Column(name = "duracaoMinutos")
    private Integer duracaoMinutos;

    @Column(name = "ativo")
    private Boolean ativo;
}
