package com.pitfrota.gestaofrotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pitfrota.gestaofrotas.model.Manutencao;

import java.util.List;

@Repository
public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {
    List<Manutencao> findByVeiculoIdOrderByDataDesc(Long veiculoId);
}