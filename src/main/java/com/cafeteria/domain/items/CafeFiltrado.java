package com.cafeteria.domain.items;

// Implementa a interface IBebida (que é um IItemPedido)
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