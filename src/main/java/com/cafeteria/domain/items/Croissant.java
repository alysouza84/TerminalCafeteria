package com.cafeteria.domain.items;

// Implementa IItemPedido diretamente
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