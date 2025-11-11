package com.cafeteria.domain.items;

public class Donuts implements IItemPedido {

    @Override
    public String getDescricao() {
        return "Donuts de Chocolate";
    }

    @Override
    public double getPreco() {
        return 6.00;
    }
}