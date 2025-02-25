package com.krtCompany.transacao_api.business.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.krtCompany.transacao_api.controller.dtos.TransacaoRequestDTO;
import com.krtCompany.transacao_api.infrastructure.exceptions.UnprocessableEntity;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {

    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<TransacaoRequestDTO>(); 

    public void adicionarTransacoes(TransacaoRequestDTO dto) {
        log.info("Iniciada o processamento de gravar transações.");

        if(dto.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("Data e hora maiores que a data e hora atual.");
            throw new UnprocessableEntity("Data e hora maiores que a data e hora atual.");
        }

        if(dto.valor() < 0) {
            log.error("Valor menor que zero.");
            throw new UnprocessableEntity("Valor menor que zero.");
        }

        listaTransacoes.add(dto);
    }

    public void limparTransacoes() {
        listaTransacoes.clear();
    }

    public List<TransacaoRequestDTO> buscarTransacoes(Integer intervaloBuscaSegundos) {
        log.info("");
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBuscaSegundos);

        return listaTransacoes.stream()
                .filter(transacao -> transacao.dataHora()
                    .isAfter(dataHoraIntervalo)).toList();
    }
}
