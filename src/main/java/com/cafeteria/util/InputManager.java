package com.cafeteria.util;

import java.util.Scanner;

/**
 * Classe utilitária para funções gerais
 */
public class InputManager {

    // Um único scanner para toda a aplicação
    private Scanner scanner;

    public InputManager() {
        scanner = new Scanner(System.in);
    }

    public String lerString() {
        return scanner.nextLine();
    }

    public int lerInt() {
        // Loop simples para garantir que o usuário digite um número
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Por favor, digite um número: ");
            }
        }
    }

    //Função para fechar o Scanner
    public void fechar() {
        scanner.close();
    }
}