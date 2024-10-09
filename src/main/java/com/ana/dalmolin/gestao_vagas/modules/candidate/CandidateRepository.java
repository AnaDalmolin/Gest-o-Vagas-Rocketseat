package com.ana.dalmolin.gestao_vagas.modules.candidate;

import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID>{

    Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);

} 