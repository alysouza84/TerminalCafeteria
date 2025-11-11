package com.cafeteria.services.factories;

import com.cafeteria.domain.items.IItemPedido;
import com.cafeteria.domain.items.Coxinha;
import com.cafeteria.domain.items.Croissant;
import com.cafeteria.domain.items.Donuts;

/**
 * PADRÃO DE PROJETO: Factory (Simple Factory)
 *
 * A mesma lógica da BebidaFactory, mas para Comidas.
 * Separamos em duas factorys para manter o Single Responsability.
 */
public class ComidaFactory {

    public IItemPedido criarComida(String tipo) {
        if (tipo.equalsIgnoreCase("COXINHA")) {
            return new Coxinha();
        } else if (tipo.equalsIgnoreCase("CROISSANT")) {
            return new Croissant();
        } else if (tipo.equalsIgnoreCase("DONUTS")) {
            return new Donuts();
        }

        throw new IllegalArgumentException("Tipo de comida inválido: " + tipo);
    }
}