package com.daniela.sistema_agendamento.infrastructure.repositories;

import com.daniela.sistema_agendamento.infrastructure.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository <Usuario, Integer> {

    Optional <Usuario> findByEmail (String email);

    @Transactional
    void deleteByEmail(String email);
}
