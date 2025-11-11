package com.cafeteria.domain.items;

/**
 * Interface comum para TODOS os itens que podem ser pedidos.
 * Define o "contrato" básico que um item deve ter:
 * uma descrição e um preço.
 * Isso permite que o carrinho de compras trate cafés e comidas
 * da mesma forma (Polimorfismo).
 */
public interface IItemPedido {
    String getDescricao();
    double getPreco();
}