package com.ana.dalmolin.gestao_vagas.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ana.dalmolin.gestao_vagas.provides.JWTProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    JWTProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                
        SecurityContextHolder.getContext().setAuthentication(null);
        String header = request.getHeader("Authorization");

        // Adicionar log para depuração
        System.out.println("Authorization header: " + header);
        if(header != null){
            String token = header.substring(7);
            var subjectToken = this.jwtProvider.validateToken(token);
            try {
                if(subjectToken.isEmpty()){
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
                request.setAttribute("company_id", subjectToken);
                UsernamePasswordAuthenticationToken auth = 
                    new UsernamePasswordAuthenticationToken(subjectToken, null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                 // Log the exception for debugging purposes
                 System.err.println("Erro ao validar o token JWT: " + e.getMessage());
                 e.printStackTrace();
                 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                 return;
            }
            
        }
        filterChain.doFilter(request, response);
    }
    
}
