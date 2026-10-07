package com.daniela.sistema_agendamento.controller;

import com.daniela.sistema_agendamento.business.ServicoService;
import com.daniela.sistema_agendamento.dto.ServicoDTO;
import com.daniela.sistema_agendamento.infrastructure.entities.Servico;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/servico")
public class ServicoController {

    private final ServicoService servicoService;

    @PostMapping
    public ResponseEntity<Void> salvarServico(@RequestBody ServicoDTO servicoDTO) {
        Servico servico = Servico.builder()
                        .nome(servicoDTO.nome())
                        .duracaoMinutos(servicoDTO.duracaoMinutos())
                        .valor(servicoDTO.valor())
                        .ativo(servicoDTO.ativo())
                        .build();

        servicoService.salvarServico(servico);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public  ResponseEntity<Servico> buscarServico(@RequestParam Integer id, @RequestBody Servico servico){
        return ResponseEntity.ok(servicoService.buscarServico(id, servico));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarServico(@RequestParam Integer id){
        servicoService.deletarServico(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizarServico(@RequestParam Integer id, @RequestBody Servico servico){
        servicoService.atualizarServico(id, servico);
        return ResponseEntity.ok().build();
    }
}
