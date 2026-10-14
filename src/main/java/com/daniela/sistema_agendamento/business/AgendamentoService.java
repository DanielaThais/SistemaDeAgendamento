package com.daniela.sistema_agendamento.business;
import com.daniela.sistema_agendamento.infrastructure.entities.Agendamento;
import com.daniela.sistema_agendamento.infrastructure.entities.Servico;
import com.daniela.sistema_agendamento.infrastructure.repositories.AgendamentoRepository;
import com.daniela.sistema_agendamento.infrastructure.repositories.ServicoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AgendamentoService {

    private final AgendamentoRepository repository;
    private final ServicoRepository servicoRepository;

    public AgendamentoService(AgendamentoRepository repository, ServicoRepository servicoRepository) {
        this.repository = repository;
        this.servicoRepository = servicoRepository;
    }

    public void salvarAgendamento(Agendamento agendamento) {
        Servico servico = servicoRepository.findById(agendamento.getServico().getId())
                        .orElseThrow( () -> new RuntimeException("Serviço não encontrado"));

        agendamento.setServico(servico);

        agendamento.setPrevisaoTermino(
                agendamento.getInicioEm().plusMinutes(servico.getDuracaoMinutos())
        );

        repository.saveAndFlush(agendamento);
    }

    public Agendamento buscarAgendamento(Integer id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException ("Agendamento não encontrado")
        );

    }

    public void deletarAgendamento(Integer id) {
        repository.deleteById(id);
    }

    public void atualizarAgendamento(Integer id, Agendamento agendamento){
        Agendamento agendamentoEntity = repository.findById(id).orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        agendamentoEntity.setInicioEm(agendamento.getInicioEm() != null
                ? agendamento.getInicioEm() : agendamentoEntity.getInicioEm());
        agendamentoEntity.setTerminoEm(agendamento.getTerminoEm() != null
                ? agendamento.getTerminoEm() : agendamentoEntity.getTerminoEm());
        agendamentoEntity.setPrevisaoTermino(agendamentoEntity.getInicioEm().plusMinutes(
                agendamentoEntity.getServico().getDuracaoMinutos()
        ));
        agendamentoEntity.setProfissional(agendamento.getProfissional() != null
                ? agendamento.getProfissional() : agendamentoEntity.getProfissional());
        agendamentoEntity.setServico(agendamento.getServico() != null
                ? agendamento.getServico() : agendamentoEntity.getServico());
        agendamentoEntity.setUsuario(agendamento.getUsuario() != null
                ? agendamento.getUsuario() : agendamentoEntity.getUsuario());

        repository.saveAndFlush(agendamentoEntity);
    }

}
