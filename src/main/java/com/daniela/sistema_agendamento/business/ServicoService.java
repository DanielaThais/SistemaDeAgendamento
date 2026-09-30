package com.daniela.sistema_agendamento.business;

import com.daniela.sistema_agendamento.infrastructure.entities.Servico;
import com.daniela.sistema_agendamento.infrastructure.repositories.ServicoRepository;
import org.springframework.stereotype.Service;

@Service
public class ServicoService {

    private final ServicoRepository repository;

    public ServicoService(ServicoRepository repository) {
        this.repository = repository;
    }

    public void salvarServico(Servico servico) {
        repository.saveAndFlush(servico);
    }

    public Servico buscarServico(Integer id, Servico servico){
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException ("Serviço não encontrado")
        );

    }

    public void deletarServico(Integer id){
        repository.deleteById(id);
    }

    public void atualizarServico(Integer id, Servico servico){
        Servico servicoEntity = repository.findById(id).orElseThrow(
                () -> new RuntimeException ("Serviço não encontrado")
        );

        Servico servicoAtualizado = Servico.builder()
                .nome(servico.getNome() != null ?
                        servico.getNome() : servicoEntity.getNome())
                .valor(servico.getValor() != null ?
                        servico.getValor() : servicoEntity.getValor())
                .duracaoMinutos(servico.getDuracaoMinutos() != null ?
                        servico.getDuracaoMinutos() : servicoEntity.getDuracaoMinutos())
                .ativo(servico.getAtivo() != null ?
                        servico.getAtivo() : servicoEntity.getAtivo())
                .id(servicoEntity.getId())
                .build();

        repository.saveAndFlush(servicoAtualizado);
    }
}
