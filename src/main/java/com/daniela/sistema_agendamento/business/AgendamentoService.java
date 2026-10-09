package com.daniela.sistema_agendamento.business;
import com.daniela.sistema_agendamento.infrastructure.entities.Agendamento;
import com.daniela.sistema_agendamento.infrastructure.repositories.AgendamentoRepository;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {

    private final AgendamentoRepository repository;

    public AgendamentoService(AgendamentoRepository repository) {
        this.repository = repository;
    }

    public void salvarAgendamento(Agendamento agendamento) {
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
        agendamentoEntity.setProfissional(agendamento.getProfissional() != null
                ? agendamento.getProfissional() : agendamentoEntity.getProfissional());
        agendamentoEntity.setServico(agendamento.getServico() != null
                ? agendamento.getServico() : agendamentoEntity.getServico());
        agendamentoEntity.setUsuario(agendamento.getUsuario() != null
                ? agendamento.getUsuario() : agendamentoEntity.getUsuario());

        repository.saveAndFlush(agendamentoEntity);
    }

}
