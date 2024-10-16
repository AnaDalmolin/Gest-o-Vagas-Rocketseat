package com.ana.dalmolin.gestao_vagas.modules.company.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ana.dalmolin.gestao_vagas.modules.company.entities.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID>{
    
} 