package com.daniela.sistema_agendamento.infrastructure.repositories;

import com.daniela.sistema_agendamento.infrastructure.entities.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ProfissionalRepository extends JpaRepository <Profissional, Integer> {

}
