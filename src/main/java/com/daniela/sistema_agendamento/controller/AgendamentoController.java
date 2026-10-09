package com.daniela.sistema_agendamento.controller;

import com.daniela.sistema_agendamento.business.AgendamentoService;
import com.daniela.sistema_agendamento.dto.AgendamentoDTO;
import com.daniela.sistema_agendamento.infrastructure.entities.Agendamento;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agendamento")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping()
    public ResponseEntity<Void> salvarAgendamento(@RequestBody AgendamentoDTO agendamentoDTO){
        Agendamento agendamento = Agendamento.builder()
                .inicioEm(agendamentoDTO.inicioEm())
                .terminoEm(agendamentoDTO.terminoEm())
                .profissional(agendamentoDTO.profissional())
                .servico(agendamentoDTO.servico())
                .status(agendamentoDTO.status())
                .usuario(agendamentoDTO.usuario())
                .build();

        agendamentoService.salvarAgendamento(agendamento);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public  ResponseEntity<Agendamento> buscarAgendamento(@RequestParam Integer id){
        return ResponseEntity.ok(agendamentoService.buscarAgendamento(id));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarAgendamento(@RequestParam Integer id){
        agendamentoService.deletarAgendamento(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizarProfissional(@RequestParam Integer id, @RequestBody AgendamentoDTO agendamentoDTO){
            Agendamento agendamento = Agendamento.builder()
                    .inicioEm(agendamentoDTO.inicioEm())
                    .terminoEm(agendamentoDTO.terminoEm())
                    .profissional(agendamentoDTO.profissional())
                    .servico(agendamentoDTO.servico())
                    .status(agendamentoDTO.status())
                    .usuario(agendamentoDTO.usuario())
                    .build();

            agendamentoService.atualizarAgendamento(id, agendamento);
            return  ResponseEntity.ok().build();
    }

}
