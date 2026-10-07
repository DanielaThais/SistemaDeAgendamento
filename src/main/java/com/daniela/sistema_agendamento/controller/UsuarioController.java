package com.daniela.sistema_agendamento.controller;

import com.daniela.sistema_agendamento.business.UsuarioService;
import com.daniela.sistema_agendamento.dto.LoginDTO;
import com.daniela.sistema_agendamento.infrastructure.entities.Usuario;
import com.daniela.sistema_agendamento.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping()
    public ResponseEntity<Void> salvarUsuario(@RequestBody Usuario usuario){
         usuarioService.salvarUsuario(usuario);
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
    public  ResponseEntity<Usuario> buscarUsuarioPorEmail(@RequestParam String email){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarUsuarioPorEmail(@RequestParam String email){
        usuarioService.deletarUsuarioPorEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizarUsuarioPorId(@RequestParam Integer id, @RequestBody Usuario usuario){
            usuarioService.atualizarUsuarioPorId(id, usuario);
            return ResponseEntity.ok().build();
    }
}
