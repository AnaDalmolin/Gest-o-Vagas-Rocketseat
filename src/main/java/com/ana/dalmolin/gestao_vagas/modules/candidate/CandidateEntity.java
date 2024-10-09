package com.ana.dalmolin.gestao_vagas.modules.candidate;

import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@Entity(name = "candidate")
public class CandidateEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String name;
    
    private String username;

    @Email(message = "Email inválido")
    private String email;
    
    @Length(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String senha;
    
    private String descricao;

    private String curriculo;

    @CreationTimestamp
    private LocalDateTime createAt;
}


