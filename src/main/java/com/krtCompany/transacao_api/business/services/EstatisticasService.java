package com.krtCompany.transacao_api.business.services;

import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.stereotype.Service;

import com.krtCompany.transacao_api.controller.dtos.EstatisticasResponseDTO;

import lombok.RequiredArgsConstructor;

import com.krtCompany.transacao_api.controller.dtos.TransacaoRequestDTO;


@Service
@RequiredArgsConstructor
public class EstatisticasService {

    public TransacaoService transacaoService;

    public EstatisticasResponseDTO calcularEstatisticasTransacoes(Integer interaloBusca) {

        long start = System.currentTimeMillis();

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(interaloBusca);

        if(transacoes.isEmpty()) {
            return new EstatisticasResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream().
                mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

        long finish = System.currentTimeMillis();
        long tempoReq = start - finish;

        System.out.println("Tempo de requisição: " + tempoReq + " ms.");

        return new EstatisticasResponseDTO(estatisticasTransacoes.getCount(), 
                                           estatisticasTransacoes.getSum(), 
                                           estatisticasTransacoes.getAverage(), 
                                           estatisticasTransacoes.getMin(), 
                                           estatisticasTransacoes.getMax());
    }
}
