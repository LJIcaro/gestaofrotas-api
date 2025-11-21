package com.pitfrota.gestaofrotas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pitfrota.gestaofrotas.model.HistoricoStatusAgendamento;

@Repository
public interface HistoricoStatusAgendamentoRepository extends JpaRepository<HistoricoStatusAgendamento, Long> {
}