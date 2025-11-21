package com.pitfrota.gestaofrotas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pitfrota.gestaofrotas.dto.AbastecimentoRequest;
import com.pitfrota.gestaofrotas.dto.AbastecimentoResponse;
import com.pitfrota.gestaofrotas.model.Abastecimento;
import com.pitfrota.gestaofrotas.model.Veiculo;
import com.pitfrota.gestaofrotas.repository.AbastecimentoRepository;
import com.pitfrota.gestaofrotas.repository.VeiculoRepository;

@Service
public class AbastecimentoService {

    @Autowired
    private AbastecimentoRepository abastecimentoRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Transactional
    public AbastecimentoResponse registrarAbastecimento(AbastecimentoRequest request) {
        Veiculo veiculo = veiculoRepository.findById(request.getVeiculoId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado com o ID: " + request.getVeiculoId()));

        Abastecimento abastecimento = new Abastecimento();
        abastecimento.setVeiculo(veiculo);
        abastecimento.setData(request.getData());
        abastecimento.setTipoCombustivel(request.getTipoCombustivel());
        abastecimento.setValor(request.getValor());
        abastecimento.setQuilometragem(request.getQuilometragem());
        abastecimento.setMotoristaResponsavel(request.getMotoristaResponsavel());

        Abastecimento salvo = abastecimentoRepository.save(abastecimento);
        return new AbastecimentoResponse(salvo);
    }
}