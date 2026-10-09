package com.daniela.sistema_agendamento.infrastructure.entities;

import com.daniela.sistema_agendamento.config.StatusAgendamento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "agendamento")
@Entity

public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", unique = true, nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "profissional_id", nullable = false)
    private Profissional profissional;

    @ManyToOne
    @JoinColumn(name = "servico_id", nullable = false)
    private Servico servico;

    @Column(name = "inicio_em")
    private LocalDateTime inicioEm;

    @Column(name = "termino_em")
    private LocalDateTime terminoEm;

    @Column(name = "status")
    private StatusAgendamento status;

}
