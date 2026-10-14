package com.daniela.sistema_agendamento.infrastructure.repositories;

import com.daniela.sistema_agendamento.config.StatusAgendamento;
import com.daniela.sistema_agendamento.infrastructure.entities.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AgendamentoRepository extends JpaRepository <Agendamento, Integer> {

    boolean existsByProfissionalIdAndStatusAndInicioEmLessThanAndPrevisaoTerminoGreaterThan(
            Integer profissionalId,
            StatusAgendamento status,
            LocalDateTime previsaoTermino,
            LocalDateTime inicioEm
            );

}
