package com.daniela.sistema_agendamento.controller;

import com.daniela.sistema_agendamento.business.ProfissionalService;
import com.daniela.sistema_agendamento.dto.LoginDTO;
import com.daniela.sistema_agendamento.dto.ProfissionalDTO;
import com.daniela.sistema_agendamento.infrastructure.entities.Profissional;
import com.daniela.sistema_agendamento.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profissional")
@RequiredArgsConstructor
public class ProfissionalController {

    private final ProfissionalService profissionalService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping()
    public ResponseEntity<Void> salvarProfissional(@RequestBody ProfissionalDTO profissionalDTO){
        Profissional profissional = Profissional.builder()
                .email(profissionalDTO.email())
                .nome(profissionalDTO.nome())
                .telefone(profissionalDTO.telefone())
                .senha(profissionalDTO.senha())
                .ativo(profissionalDTO.ativo())
                .build();

        profissionalService.salvarProfissional(profissional);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.email(),
                        loginDTO.senha()
                )
        );

        String token = tokenService.gerarToken(loginDTO.email());

        return ResponseEntity.ok(token);
    }

    @GetMapping
    public  ResponseEntity<Profissional> buscarProfissionalPorEmail(@RequestParam String email){
        return ResponseEntity.ok(profissionalService.buscarProfissionalPorEmail(email));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarProfissionalPorEmail(@RequestParam String email){
        profissionalService.deletarProfissionalPorEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizarProfissionalPorId(@RequestParam Integer id, @RequestBody Profissional profissional){
            profissionalService.atualizarProfissionalPorId(id, profissional);
            return ResponseEntity.ok().build();
    }
}
