package com.pitfrota.gestaofrotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pitfrota.gestaofrotas.model.Abastecimento;

@Repository
public interface AbastecimentoRepository extends JpaRepository<Abastecimento, Long> {
}