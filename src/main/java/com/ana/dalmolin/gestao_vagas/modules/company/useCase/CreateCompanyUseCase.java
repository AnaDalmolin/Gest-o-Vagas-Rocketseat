package com.ana.dalmolin.gestao_vagas.modules.company.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

import com.ana.dalmolin.gestao_vagas.exceptions.UserFoundException;
import com.ana.dalmolin.gestao_vagas.modules.company.entities.CompanyEntity;
import com.ana.dalmolin.gestao_vagas.modules.company.repository.CompanyRepository;
@Service
public class CreateCompanyUseCase {
    
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional
    public CompanyEntity execute(CompanyEntity companyEntity) {
        Optional<CompanyEntity> existingCompany = this.companyRepository
            .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail());

        if (existingCompany.isPresent()) {
            throw new UserFoundException();
        }

        var password = passwordEncoder.encode(companyEntity.getPassword()); 
        companyEntity.setPassword(password);

        return this.companyRepository.save(companyEntity);
    }
}
