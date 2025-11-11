package com.cafeteria.services.factories;

import com.cafeteria.domain.pagamento.IMetodoPagamento;
import com.cafeteria.domain.pagamento.PagamentoCartao;
import com.cafeteria.domain.pagamento.PagamentoPix;

/**
 * PADRÃO DE PROJETO: Factory (Simple Factory)
 *
 * A factory de métodos de pagamento.
 */
public class PagamentoFactory {

    public IMetodoPagamento criarMetodoPagamento(String tipo) {
        if (tipo.equalsIgnoreCase("PIX")) {
            return new PagamentoPix();
        } else if (tipo.equalsIgnoreCase("CARTAO")) {
            return new PagamentoCartao();
        }

        throw new IllegalArgumentException("Tipo de pagamento inválido: " + tipo);
    }
}