package com.cafeteria.domain.items;

public class CafeFiltrado implements IBebida {

    @Override
    public String getDescricao() {
        return "Café Filtrado (Coado)";
    }

    @Override
    public double getPreco() {
        return 4.00; // Preço base
    }
}