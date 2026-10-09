package com.daniela.sistema_agendamento.infrastructure.repositories;

import com.daniela.sistema_agendamento.infrastructure.entities.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository <Agendamento, Integer> {

}
