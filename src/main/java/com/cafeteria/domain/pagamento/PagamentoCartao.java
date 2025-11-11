package com.cafeteria.domain.pagamento;

//forma de pagamento Cartão
public class PagamentoCartao implements IMetodoPagamento {
    @Override
    public void processar(double valor) {
        System.out.println("Pagamento de R$" + valor + " via Cartão processado.");
        System.out.println("(Simulando comunicação com a maquininha...)");
    }
}