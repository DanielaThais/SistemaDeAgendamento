package com.daniela.sistema_agendamento.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "usuario")
@Entity

public class Usuario<string> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "email", unique = true, nullable = false)
    private string email;

    @Column(name = "nome", nullable = false)
    private string nome;

    @Column(name = "telefone", nullable = false)
    private string telefone;

}
