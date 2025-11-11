package com.cafeteria.domain.items;

/**
 * Esta é uma "Interface Marcadora".
 * Ela extende IItemPedido, mas não adiciona novos métodos.
 *
 * Sua função é sinalizar quais IItemPedido são
 * Bebidas, para que o Padrão Decorator (os adicionais)
 * saibam que só pode ser aplicado a elas.
 *
 * (Ex: Não da para adicionar Leite a uma Coxinha).
 */
public interface IBebida extends IItemPedido {
    // Nenhuns métodos novos, apenas marcação para uso futuro
}