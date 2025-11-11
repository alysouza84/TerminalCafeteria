package com.cafeteria;

// Importa todas as dependências que precisam ser "montadas"
import com.cafeteria.services.factories.BebidaFactory;
import com.cafeteria.services.factories.ComidaFactory;
import com.cafeteria.services.factories.PagamentoFactory;
import com.cafeteria.services.processing.PaymentProcessor;
import com.cafeteria.services.queue.FilaDePedidos;
import com.cafeteria.terminal.TerminalKiosk;
import com.cafeteria.util.InputManager;

/**
 * Ponto de Entrada (main) da Aplicação.
 *
 * PADRÃO DE PROJETO: Inversão de Controle (IoC) / Injeção de Dependência (DI)
 *
 * Esta classe atua como o "Container de Injeção" (ou "Injetor").
 * A sua responsabilidade é:
 * 1. CRIAR todas as dependências (Fábricas, Processadores).
 * 2. OBTER a instância do Singleton (a Fila).
 * 3. INJETAR (passar) essas dependências para a classe
 * que precisa delas (o TerminalKiosk) através do construtor.
 */
public class CafeteriaApp {

    public static void main(String[] args) {

        System.out.println("APP: Inicializando sistema...");

        // 1. CRIANDO as dependências (serviços e fábricas)
        // É aqui que os 'new' dos serviços acontecem.
        InputManager input = new InputManager();
        BebidaFactory bebidaFactory = new BebidaFactory();
        ComidaFactory comidaFactory = new ComidaFactory();
        PagamentoFactory pagamentoFactory = new PagamentoFactory();
        PaymentProcessor paymentProcessor = new PaymentProcessor();

        // 2. OBTENDO a instância do Singleton
        // Note que não usamos 'new', usamos o método estático
        // para garantir que pegamos a instância ÚNICA.
        FilaDePedidos filaUnica = FilaDePedidos.getInstancia();

        // (Podemos "provar" o singleton)
        // FilaDePedidos fila2 = FilaDePedidos.getInstancia();
        // System.out.println(filaUnica == fila2); // Daria 'true'

        // 3. INJETANDO TUDO no construtor do Terminal
        // O TerminalKiosk é criado e recebe todas as suas
        // "ferramentas" prontas para usar.
        TerminalKiosk terminal1 = new TerminalKiosk(
                bebidaFactory,
                comidaFactory,
                pagamentoFactory,
                paymentProcessor,
                filaUnica,  // A instância do Singleton é injetada
                input
        );

        // 4. Inicia a aplicação
        // O main delega o controle total para o Terminal.
        terminal1.iniciarAtendimento();

        // 5. Limpeza
        // Quando o loop do terminal quebrar (opção 4), fechamos o scanner.
        System.out.println("APP: Encerrando sistema.");
        input.fechar();
    }
}