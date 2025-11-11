package com.cafeteria.services.factories;

import com.cafeteria.domain.items.IBebida;
import com.cafeteria.domain.items.Espresso;
import com.cafeteria.domain.items.CafeFiltrado;

/**
 * PADRÃO DE PROJETO: Factory (Simple Factory)
 *
 * Esta classe só sabe cirar objetos IBebida.
 *
 * Ela esconde a lógica (o 'new') do Terminal.
 * O Terminal apenas pede: "Dê-me um 'ESPRESSO'".
 */
public class BebidaFactory {

    public IBebida criarBebida(String tipo) {
        if (tipo.equalsIgnoreCase("ESPRESSO")) {
            return new Espresso();
        } else if (tipo.equalsIgnoreCase("FILTRADO")) {
            return new CafeFiltrado();
        }

        // Lança uma exceção se o tipo for desconhecido
        throw new IllegalArgumentException("Tipo de bebida inválido: " + tipo);
    }
}