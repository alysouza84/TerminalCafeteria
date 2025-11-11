package com.cafeteria.domain.items;

// Implementa IItemPedido diretamente (NÃO é uma IBebida)
public class Coxinha implements IItemPedido {

    @Override
    public String getDescricao() {
        return "Coxinha de Frango";
    }

    @Override
    public double getPreco() {
        return 7.50;
    }
}