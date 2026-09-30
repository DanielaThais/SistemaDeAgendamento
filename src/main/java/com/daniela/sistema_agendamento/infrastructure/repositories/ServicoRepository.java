package com.daniela.sistema_agendamento.infrastructure.repositories;

import com.daniela.sistema_agendamento.infrastructure.entities.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {

    List<Servico> findByAtivoTrue();
}
