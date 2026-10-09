package com.daniela.sistema_agendamento.dto;

import com.daniela.sistema_agendamento.infrastructure.entities.Usuario;

public record ProfissionalDTO(Usuario usuario, Boolean ativo) {
}
