package com.cafeteria;

// Import das dependencias
import com.cafeteria.services.factories.BebidaFactory;
import com.cafeteria.services.factories.ComidaFactory;
import com.cafeteria.services.factories.PagamentoFactory;
import com.cafeteria.services.processing.PaymentProcessor;
import com.cafeteria.services.queue.FilaDePedidos;
import com.cafeteria.terminal.TerminalKiosk;
import com.cafeteria.util.InputManager;

/**
 * Ponto de Entrada (main) da Aplicação.
 * PADRÃO DE PROJETO: Inversão de Controle (IoC) / Injeção de Dependência (DI)
 *
 * Esta classe atua como o injetor. Ela:
 * 1. CRIA todas as dependências (Factory e Processors).
 * 2. OBTEM a instância do Singleton (a Fila).
 * 3. INJETA (passa) essas dependências para a classe que precisa delas (o TerminalKiosk) através do construtor.
 */
public class CafeteriaApp {

    public static void main(String[] args) {

        System.out.println("APP: Inicializando sistema...");

        // 1. CRIA as dependências (services e factory)
        InputManager input = new InputManager();
        BebidaFactory bebidaFactory = new BebidaFactory();
        ComidaFactory comidaFactory = new ComidaFactory();
        PagamentoFactory pagamentoFactory = new PagamentoFactory();
        PaymentProcessor paymentProcessor = new PaymentProcessor();

        // 2. OBTEM a instância do Singleton
        // usando o método estático para garantir que pegamos a instância ÚNICA.
        FilaDePedidos filaUnica = FilaDePedidos.getInstancia();

        // Teste do singleton:
        // FilaDePedidos fila2 = FilaDePedidos.getInstancia();
        // System.out.println(filaUnica == fila2); // Daria 'true'

        // 3. INJETA TUDO no construtor do Terminal para uso
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
        // Quando o loop do terminal quebrar (opção 4), o scanner é fechado.
        System.out.println("APP: Encerrando sistema.");
        input.fechar();
    }
}