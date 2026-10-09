package com.daniela.sistema_agendamento.dto;

import com.daniela.sistema_agendamento.config.StatusAgendamento;
import com.daniela.sistema_agendamento.infrastructure.entities.Profissional;
import com.daniela.sistema_agendamento.infrastructure.entities.Servico;
import com.daniela.sistema_agendamento.infrastructure.entities.Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public record AgendamentoDTO(Usuario usuario, Profissional profissional, Servico servico, LocalDateTime inicioEm, LocalDateTime terminoEm, StatusAgendamento status) {
}

