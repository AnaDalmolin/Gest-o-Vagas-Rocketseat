package com.ana.dalmolin.gestao_vagas.modules.company.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import java.util.UUID;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Entity(name = "company")
@Data
public class CompanyEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String username;

    @Email(message = "Email inválido")
    private String email;

    @Length(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String password;
    private String website;
    private String descricao;

    @CreationTimestamp
    private LocalDateTime createAt;
}




