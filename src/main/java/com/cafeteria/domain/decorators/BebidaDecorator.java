package com.cafeteria.domain.decorators;

import com.cafeteria.domain.items.IBebida;

/**
 * PADRÃO DE PROJETO: Decorator (Abstrato)
 *
 * classe base para todos os "adicionais".
 * Ela "embrulha" (decora) uma IBebida existente.
 *
 * 1. Ela implementa IBebida, para que possa ser tratada
 * como a bebida original.
 * 2. Ela armazena uma referência à bebida que
 * está a ser embrulhada.
 */
public abstract class BebidaDecorator implements IBebida {

    // A referência para o objeto que está a ser "embrulhado"
    // (Pode ser um Espresso, ou pode ser um Espresso já embrulhado em Leite)
    protected IBebida bebidaEnvolvida;

    public BebidaDecorator(IBebida bebida) {
        this.bebidaEnvolvida = bebida;
    }

    /**
     * Por padrão, o decorator "delega" a chamada
     * para o objeto embrulhado.
     * As classes filhas (AdicionalAcucar) irão sobrescrever
     * este comportamento.
     */
    @Override
    public String getDescricao() {
        return bebidaEnvolvida.getDescricao();
    }

    @Override
    public double getPreco() {
        return bebidaEnvolvida.getPreco();
    }
}