package com.cafeteria.domain.items;

/**
 * Esta é uma "Interface Marcadora".
 * Ela estende IItemPedido, mas não adiciona novos métodos.
 *
 * Seu único propósito é "marcar" quais IItemPedido são
 * Bebidas, para que o Padrão Decorator (os adicionais)
 * saiba que só pode ser aplicado a elas.
 *
 * (Não podemos adicionar Leite a uma Coxinha).
 */
public interface IBebida extends IItemPedido {
    // Nenhuns métodos novos, apenas marcação
}