package com.daniela.sistema_agendamento.dto;

import java.math.BigDecimal;

public record ServicoDTO(String nome, BigDecimal valor, Integer duracaoMinutos, Boolean ativo) {
}
