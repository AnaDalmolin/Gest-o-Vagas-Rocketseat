package com.ana.dalmolin.gestao_vagas.modules.company.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ana.dalmolin.gestao_vagas.modules.company.entities.JobEntity;
import com.ana.dalmolin.gestao_vagas.modules.company.repository.JobRepository;

@Service
public class CreateJobUseCase {
    
    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(JobEntity jobEntity){
        return this.jobRepository.save(jobEntity);
    }

}
