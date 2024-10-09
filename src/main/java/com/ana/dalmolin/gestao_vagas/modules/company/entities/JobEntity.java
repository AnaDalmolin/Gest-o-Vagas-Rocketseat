package com.ana.dalmolin.gestao_vagas.modules.company.entities;

import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

import javax.persistence.*;
import lombok.Data;

@Entity(name = "job")
@Data
public class JobEntity {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String description;

    private String benefits;

    private String level;

    @ManyToOne()
    @JoinColumn(name = "company_id")
    private CompanyEntity companyEntity;

    @Column(name = "company_id", insertable = false, updatable = false)
    private UUID companyId;
    
    @CreationTimestamp
    private LocalDateTime createAt;

}
