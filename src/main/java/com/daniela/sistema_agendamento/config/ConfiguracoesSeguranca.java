package com.daniela.sistema_agendamento.config;

import com.daniela.sistema_agendamento.security.FiltroAutenticacaoJWT;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class ConfiguracoesSeguranca {

    private final FiltroAutenticacaoJWT filtroAutenticacaoJWT;

    public ConfiguracoesSeguranca (FiltroAutenticacaoJWT filtroAutenticacaoJWT){
        this.filtroAutenticacaoJWT = filtroAutenticacaoJWT;
    }

    @Bean
    public SecurityFilterChain configurarSeguranca(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/usuario", "/usuario/login").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        filtroAutenticacaoJWT,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuracao) throws Exception {
        return configuracao.getAuthenticationManager();
    }

}