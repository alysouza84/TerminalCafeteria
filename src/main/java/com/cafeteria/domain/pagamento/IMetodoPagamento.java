package com.cafeteria.domain.pagamento;

/**
 * Interface (Contrato) para todos os métodos de pagamento.
 * Usada pelo Padrão Factory (para criar) e
 * pelo Padrão DI (por Método, para injetar no PaymentProcessor).
 */
public interface IMetodoPagamento {
    void processar(double valor);
}