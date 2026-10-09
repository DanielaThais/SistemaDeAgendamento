package com.daniela.sistema_agendamento.business;

import com.daniela.sistema_agendamento.infrastructure.entities.Profissional;
import com.daniela.sistema_agendamento.infrastructure.repositories.ProfissionalRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfissionalService {

    private final ProfissionalRepository repository;
    private final PasswordEncoder passwordEncoder;

    public ProfissionalService(
            ProfissionalRepository repository,
            PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void salvarProfissional(Profissional profissional) {
        profissional.setSenha(passwordEncoder.encode(profissional.getSenha()));
        repository.saveAndFlush(profissional);
    }

    public Profissional buscarProfissionalPorEmail (String email){
        return repository.findByEmail(email).orElseThrow(
                () -> new RuntimeException ("E-mail não encontrado")
        );
    }

    public void deletarProfissionalPorEmail (String email){
        repository.deleteByEmail(email);
    }

    public void atualizarProfissionalPorId(Integer id, Profissional profissional){
        Profissional profissionalEntity = repository.findById(id).orElseThrow(
                () -> new RuntimeException ("Profissional não encontrado")
        );
        Profissional profissionalAtualizado = Profissional.builder()
                .email(profissional.getEmail() != null ?
                        profissional.getEmail() : profissionalEntity.getEmail())
                .nome(profissional.getNome() != null ?
                        profissional.getNome() : profissionalEntity.getNome())
                .telefone(profissional.getTelefone() != null ?
                        profissional.getTelefone() : profissionalEntity.getTelefone())
                .ativo(profissional.getAtivo() != null ?
                        profissional.getAtivo() : profissionalEntity.getAtivo())
                .id(profissionalEntity.getId())
                        .build();

        repository.saveAndFlush(profissionalAtualizado);
    }

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
