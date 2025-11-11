package com.cafeteria.domain.items;

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