package com.pitfrota.gestaofrotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pitfrota.gestaofrotas.model.Motorista;

import java.util.Optional;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Long> {
    Optional<Motorista> findByEmail(String email); // Útil para login
    Optional<Motorista> findByCpf(String cpf);   // Para verificar unicidade
}