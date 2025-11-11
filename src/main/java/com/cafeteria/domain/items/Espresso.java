package com.cafeteria.domain.items;

// Implementa a interface IBebida (que é um IItemPedido)
public class Espresso implements IBebida {

    @Override
    public String getDescricao() {
        return "Espresso";
    }

    @Override
    public double getPreco() {
        return 5.00; // Preço base
    }
}