package com.cafeteria.domain.items;

public class Croissant implements IItemPedido {

    @Override
    public String getDescricao() {
        return "Croissant amanteigado";
    }

    @Override
    public double getPreco() {
        return 8.00;
    }
}