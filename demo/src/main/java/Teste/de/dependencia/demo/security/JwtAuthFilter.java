package Teste.de.dependencia.demo.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import Teste.de.dependencia.demo.service.JwtService;
import jakarta.servlet.FilterChain;


import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // deixa passar, o SecurityConfig decide se bloqueia
            return;
        }

        String token = authHeader.substring(7); // remove o "Bearer " do começo

        String email = jwtService.validateToken(token); // valida e pega o subject (email)

        if (email != null) {
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());

            SecurityContextHolder.getContext().setAuthentication(auth); // "avisa" o Spring que esse usuário tá autenticado
        }

        filterChain.doFilter(request, response); // continua o fluxo normal
    }
}
