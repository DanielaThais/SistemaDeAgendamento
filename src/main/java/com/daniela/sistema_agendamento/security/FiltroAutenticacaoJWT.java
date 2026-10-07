package com.daniela.sistema_agendamento.security;

import com.daniela.sistema_agendamento.business.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class FiltroAutenticacaoJWT extends OncePerRequestFilter {

        private final TokenService tokenService;
        private final UsuarioService usuarioService;

        public FiltroAutenticacaoJWT(
                TokenService tokenService,
                UsuarioService usuarioService
        ){
            this.tokenService = tokenService;
            this.usuarioService = usuarioService;
        };

        @Override
        protected void doFilterInternal(
                HttpServletRequest request,
                HttpServletResponse response,
                FilterChain filterChain) throws ServletException, IOException {

            String token = request.getHeader("Authorization");

            if (token != null && token.startsWith("Bearer ")){
                token = token.substring(7); // exclui o Bearer do token e o espaço

                String email = tokenService.extrairEmail(token);
                UserDetails usuario = usuarioService.loadUserByUsername(email);

                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                usuario,
                                null,
                                usuario.getAuthorities()
                        )
                );
            }

            filterChain.doFilter(request, response);
       }

         }

