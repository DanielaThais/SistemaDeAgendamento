package com.daniela.sistema_agendamento.controller;

import com.daniela.sistema_agendamento.business.ProfissionalService;
import com.daniela.sistema_agendamento.dto.ProfissionalDTO;
import com.daniela.sistema_agendamento.infrastructure.entities.Profissional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profissional")
@RequiredArgsConstructor
public class ProfissionalController {

    private final ProfissionalService profissionalService;

    @PostMapping()
    public ResponseEntity<Void> salvarProfissional(@RequestBody ProfissionalDTO profissionalDTO){
        Profissional profissional = Profissional.builder()
                .usuario(profissionalDTO.usuario())
                .ativo(profissionalDTO.ativo())
                .build();

        profissionalService.salvarProfissional(profissional);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public  ResponseEntity<Profissional> buscarProfissional(@RequestParam Integer id){
        return ResponseEntity.ok(profissionalService.buscarProfisional(id));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarProfissional(@RequestParam Integer id){
        profissionalService.deletarProfissional(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizarProfissional(@RequestParam Integer id, @RequestBody ProfissionalDTO profissionalDTO){
            Profissional profissional = Profissional.builder()
                    .usuario(profissionalDTO.usuario())
                    .ativo(profissionalDTO.ativo())
                    .build();

            profissionalService.atualizarProfissional(id, profissional);
            return  ResponseEntity.ok().build();
    }

}
