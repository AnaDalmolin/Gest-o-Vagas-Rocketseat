package com.ana.dalmolin.gestao_vagas.modules.company.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ana.dalmolin.gestao_vagas.exceptions.UserFoundException;
import com.ana.dalmolin.gestao_vagas.modules.company.entities.CompanyEntity;
import com.ana.dalmolin.gestao_vagas.modules.company.repository.CompanyRepository;
@Repository
public class CreateCompanyUseCase {
    
    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute (CompanyEntity companyEntity){
        this.companyRepository.
        findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail()).
        ifPresent((user)->{
            throw new UserFoundException();
        });
        return this.companyRepository.save(companyEntity);
        
    }
}
