package com.cafeteria.services.processing;

import com.cafeteria.domain.pagamento.IMetodoPagamento;

/**
 * PRINCÍPIO: Responsabilidade Única (SRP)
 *
 * Esta classe tem uma responsabilidade: processar os pagamentos.
 * Ela não sabe criar pagamentos (isso é da Factory).
 * Ela não sabe orquestrar o pedido (isso é do Terminal).
 */
public class PaymentProcessor {

    /**
     * PADRÃO DE PROJETO: Injeção de Dependência (DI) por Método
     *
     * esta classe não armazena o IMetodoPagamento.
     * Ela o RECEBE como um parâmetro no método onde ele é usado.
     */
    public void processar(double valor, IMetodoPagamento metodo) {
        System.out.println("PROCESSADOR: Iniciando processamento de R$" + valor);

        // Delega a ação para o método injetado
        metodo.processar(valor);

        System.out.println("PROCESSADOR: Pagamento concluído.");
    }
}