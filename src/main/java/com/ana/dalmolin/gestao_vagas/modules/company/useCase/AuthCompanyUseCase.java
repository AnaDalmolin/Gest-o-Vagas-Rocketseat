package com.ana.dalmolin.gestao_vagas.modules.company.useCase;

import java.time.Duration;
import java.time.Instant;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ana.dalmolin.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.ana.dalmolin.gestao_vagas.modules.company.repository.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class AuthCompanyUseCase {
    
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${security.token.secret}")
    private String secreteKey;
    

    public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException{
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername())
        .orElseThrow(()->{
            throw new UsernameNotFoundException("Username/password incorrect");
        });
        
        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if(!passwordMatches){
            throw new AuthenticationException("Invalid password");
        }
        Algorithm algorithm = Algorithm.HMAC256(secreteKey);
        var token = JWT.create().withIssuer("javagas").
            withExpiresAt(Instant.now().plus(Duration.ofHours(2))).
            withSubject(company.getId().toString())
            .sign(algorithm);

        return token;
    }
}
