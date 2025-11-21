package com.pitfrota.gestaofrotas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pitfrota.gestaofrotas.dto.ManutencaoRequest;
import com.pitfrota.gestaofrotas.dto.ManutencaoResponse;
import com.pitfrota.gestaofrotas.service.ManutencaoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/manutencoes")
public class ManutencaoController {

    @Autowired
    private ManutencaoService manutencaoService;

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<?> registrarManutencao(@RequestBody ManutencaoRequest request) {
        try {
            ManutencaoResponse response = manutencaoService.registrarManutencao(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/veiculo/{veiculoId}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<?> listarPorVeiculo(@PathVariable Long veiculoId) {
        try {
            List<ManutencaoResponse> response = manutencaoService.listarManutencoesPorVeiculo(veiculoId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}