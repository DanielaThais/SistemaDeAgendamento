package com.daniela.sistema_agendamento.business;

import com.daniela.sistema_agendamento.infrastructure.entities.Profissional;
import com.daniela.sistema_agendamento.infrastructure.repositories.ProfissionalRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfissionalService {

    private final ProfissionalRepository repository;

    public ProfissionalService(ProfissionalRepository repository) {
        this.repository = repository;
    }

    public void salvarProfissional(Profissional profissional) {
        repository.saveAndFlush(profissional);
    }

    public Profissional buscarProfisional(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException ("Profissional não encontrado")
        );

    }

    public void deletarProfissional(Integer id) {
        repository.deleteById(id);
    }

    public void atualizarProfissional(Integer id, Profissional profissional){
        Profissional profissionalEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado"));
        profissionalEntity.setUsuario(profissional.getUsuario() != null
                ? profissional.getUsuario() : profissionalEntity.getUsuario());

        profissionalEntity.setAtivo(profissional.getAtivo() != null
                ? profissional.getAtivo() : profissionalEntity.getAtivo());

        repository.saveAndFlush(profissionalEntity);
    }

}
