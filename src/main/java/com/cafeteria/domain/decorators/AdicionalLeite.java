package com.cafeteria.domain.decorators;

import com.cafeteria.domain.items.IBebida;

/**
 * PADRÃO DE PROJETO: Decorator (Concreto)
 *
 * Esta é a implementação de um adicional.
 * Ele estende o Decorator abstrato.
 */
public class AdicionalLeite extends BebidaDecorator {

    public AdicionalLeite(IBebida bebida) {
        super(bebida);
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + ", com Leite";
    }

    @Override
    public double getPreco() {
        return super.getPreco() + 1.50;
    }
}