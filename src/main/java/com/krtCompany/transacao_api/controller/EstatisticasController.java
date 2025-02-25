package com.krtCompany.transacao_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krtCompany.transacao_api.business.services.EstatisticasService;
import com.krtCompany.transacao_api.controller.dtos.EstatisticasResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/estatistica")
@RequiredArgsConstructor
public class EstatisticasController {
    private final EstatisticasService estatisticasService;

    public ResponseEntity<EstatisticasResponseDTO> buscarEstatisticas(
        @RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca) {
            return ResponseEntity.ok(estatisticasService.calcularEstatisticasTransacoes(intervaloBusca));
    }
}  
