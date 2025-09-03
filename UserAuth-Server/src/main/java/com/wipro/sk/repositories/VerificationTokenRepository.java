package com.wipro.sk.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.sk.dto.Token;

@Repository
public interface VerificationTokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(String token);
}
