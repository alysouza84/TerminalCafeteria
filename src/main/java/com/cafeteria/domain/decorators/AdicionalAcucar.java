package com.cafeteria.domain.decorators;

import com.cafeteria.domain.items.IBebida;

/**
 * PADRÃO DE PROJETO: Decorator (Concreto)
 *
 * Esta é a implementação de um adicional.
 * Ele estende o Decorator abstrato.
 */
public class AdicionalAcucar extends BebidaDecorator {

    public AdicionalAcucar(IBebida bebida) {
        // Passa a bebida a ser embrulhada para o construtor pai
        super(bebida);
    }

    /**
     * A "mágica" do Decorator acontece aqui.
     * Ele chama o método original (super.getDescricao())
     * E ADICIONA o seu próprio comportamento.
     */
    @Override
    public String getDescricao() {
        return super.getDescricao() + ", com Açúcar";
    }

    @Override
    public double getPreco() {
        // Pega o preço original e SOMA o seu preço
        return super.getPreco() + 0.25;
    }
}