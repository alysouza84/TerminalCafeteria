package com.cafeteria.domain.pagamento;

public class PagamentoPix implements IMetodoPagamento {
    @Override
    public void processar(double valor) {
        System.out.println("Pagamento de R$" + valor + " via PIX processado.");
        System.out.println("(Simulando chamada à API do Banco Central...)");
    }
}