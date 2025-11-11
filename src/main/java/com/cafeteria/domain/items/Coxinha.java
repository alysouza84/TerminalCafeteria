package com.cafeteria.domain.items;

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