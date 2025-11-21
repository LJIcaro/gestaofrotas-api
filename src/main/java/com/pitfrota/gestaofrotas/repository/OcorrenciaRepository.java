package com.pitfrota.gestaofrotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pitfrota.gestaofrotas.model.Ocorrencia;

import java.util.List;

@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {
    List<Ocorrencia> findAllByOrderByDataHoraRegistroDesc();
}