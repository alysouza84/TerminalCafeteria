package com.cafeteria.terminal;

import com.cafeteria.domain.items.IBebida;
import com.cafeteria.domain.items.IItemPedido;
import com.cafeteria.domain.pagamento.IMetodoPagamento;
import com.cafeteria.domain.decorators.AdicionalAcucar;
import com.cafeteria.domain.decorators.AdicionalLeite;
import com.cafeteria.services.factories.BebidaFactory;
import com.cafeteria.services.factories.ComidaFactory;
import com.cafeteria.services.factories.PagamentoFactory;
import com.cafeteria.services.processing.PaymentProcessor;
import com.cafeteria.services.queue.FilaDePedidos;
import com.cafeteria.util.InputManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta é a classe "orquestradora".
 * A sua responsabilidade (SRP) é gerir o fluxo de interação
 * com o cliente (o menu) e DELEGAR o trabalho para
 * as outras classes (Fábricas, Processadores, Fila).
 */
public class TerminalKiosk {

    // --- DEPENDÊNCIAS ---
    // O Terminal DEPENDE destas "ferramentas" para funcionar.
    private final BebidaFactory bebidaFactory;
    private final ComidaFactory comidaFactory;
    private final PagamentoFactory pagamentoFactory;
    private final PaymentProcessor paymentProcessor;
    private final FilaDePedidos filaDePedidos; // O Singleton
    private final InputManager input;

    private List<IItemPedido> pedidoAtual;

    /**
     * PADRÃO DE PROJETO: Injeção de Dependência (DI) por Construtor
     *
     * O Terminal NÃO dá 'new' nas suas dependências essenciais.
     * Ele as RECEBE "de fora" (do CafeteriaApp) quando é construído.
     *
     * Isso o torna DESACOPLADO. O Terminal não sabe como
     * a FilaDePedidos é feita (Singleton? Normal?), ele apenas
     * a usa. Ele não conhece as classes concretas das fábricas.
     */
    public TerminalKiosk(BebidaFactory bFactory,
                         ComidaFactory cFactory,
                         PagamentoFactory pFactory,
                         PaymentProcessor pProcessor,
                         FilaDePedidos fila,
                         InputManager input) {

        System.out.println("DI: Injetando fábricas, processador e fila no TerminalKiosk.");

        this.bebidaFactory = bFactory;
        this.comidaFactory = cFactory;
        this.pagamentoFactory = pFactory;
        this.paymentProcessor = pProcessor;
        this.filaDePedidos = fila; // Recebendo a instância ÚNICA do Singleton via DI
        this.input = input;

        this.pedidoAtual = new ArrayList<>();
    }

    /**
     * Ponto de entrada principal do fluxo do terminal.
     */
    public void iniciarAtendimento() {
        System.out.println("=== BEM-VINDO À CAFETARIA DO PROGRAMADOR ===");

        // Loop principal do pedido
        while (true) {
            exibirMenuPrincipal();
            int escolha = input.lerInt();

            if (escolha == 1) {
                handleMenuCafe();
            } else if (escolha == 2) {
                handleMenuComida();
            } else if (escolha == 3) {
                if (pedidoAtual.isEmpty()) {
                    System.out.println("O seu carrinho está vazio.");
                } else {
                    handleFinalizarPedido();
                    // Limpa o carrinho para o próximo cliente
                    this.pedidoAtual = new ArrayList<>();
                    System.out.println("\n\n=== BEM-VINDO À CAFETARIA DO PROGRAMADOR ===");
                }
            } else if (escolha == 4) {
                System.out.println("Atendimento cancelado.");
                break;
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }

    private void exibirMenuPrincipal() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        exibirCarrinho();
        System.out.println("1. Pedir um Café");
        System.out.println("2. Pedir uma Comida");
        System.out.println("3. Finalizar Pedido");
        System.out.println("4. Cancelar e Sair");
        System.out.print("Escolha uma opção: ");
    }

    // --- 1. Lógica dos Cafés ---

    private void handleMenuCafe() {
        System.out.println("\n-- Cafés --");
        System.out.println("1. Espresso (R$ 5.00)");
        System.out.println("2. Café Filtrado (R$ 4.00)");
        System.out.println("3. Voltar");
        System.out.print("Escolha: ");
        int escolha = input.lerInt();

        IBebida bebida;
        try {
            if (escolha == 1) {
                // USA A FÁBRICA: Pede a criação da bebida
                bebida = bebidaFactory.criarBebida("ESPRESSO");
            } else if (escolha == 2) {
                // USA A FÁBRICA: Pede a criação da bebida
                bebida = bebidaFactory.criarBebida("FILTRADO");
            } else {
                return; // Volta ao menu anterior
            }

            // Pergunta pelos adicionais (Decorator)
            bebida = handleMenuAdicionais(bebida);

            // Adiciona a bebida (original ou decorada) ao carrinho
            pedidoAtual.add(bebida);
            System.out.println("Item adicionado: " + bebida.getDescricao());

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * Aplica o Padrão Decorator dinamicamente
     */
    private IBebida handleMenuAdicionais(IBebida bebidaBase) {
        IBebida bebidaDecorada = bebidaBase;

        while (true) {
            System.out.println("\n-- Adicionais para: " + bebidaDecorada.getDescricao() + " (Preço atual: R$" + bebidaDecorada.getPreco() + ") --");
            System.out.println("1. Adicionar Açúcar (+ R$ 0.25)");
            System.out.println("2. Adicionar Leite (+ R$ 1.50)");
            System.out.println("3. Concluir adicionais deste item");
            System.out.print("Escolha: ");
            int escolha = input.lerInt();

            if (escolha == 1) {
                // PADRÃO DECORATOR: "Embrulha" a bebida atual
                // com o decorador de Açúcar.
                bebidaDecorada = new AdicionalAcucar(bebidaDecorada);
                System.out.println("Açúcar adicionado.");
            } else if (escolha == 2) {
                // PADRÃO DECORATOR: "Embrulha" a bebida atual
                // com o decorador de Leite.
                bebidaDecorada = new AdicionalLeite(bebidaDecorada);
                System.out.println("Leite adicionado.");
            } else if (escolha == 3) {
                break; // Sai do loop de adicionais
            } else {
                System.out.println("Opção inválida.");
            }
        }
        return bebidaDecorada; // Retorna a bebida (original ou embrulhada)
    }

    // --- 2. Lógica das Comidas ---

    private void handleMenuComida() {
        System.out.println("\n-- Comidas --");
        System.out.println("1. Coxinha (R$ 7.50)");
        System.out.println("2. Croissant (R$ 8.00)");
        System.out.println("3. Donuts (R$ 6.00)");
        System.out.println("4. Voltar");
        System.out.print("Escolha: ");
        int escolha = input.lerInt();

        IItemPedido comida;
        try {
            if (escolha == 1) {
                comida = comidaFactory.criarComida("COXINHA");
            } else if (escolha == 2) {
                comida = comidaFactory.criarComida("CROISSANT");
            } else if (escolha == 3) {
                comida = comidaFactory.criarComida("DONUTS");
            } else {
                return; // Volta
            }

            pedidoAtual.add(comida);
            System.out.println("Item adicionado: " + comida.getDescricao());

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // --- 3. Lógica de Finalização ---

    private void handleFinalizarPedido() {
        System.out.println("\n--- FINALIZANDO PEDIDO ---");

        // 1. Calcular o total
        double total = 0.0;
        System.out.println("Itens do seu pedido:");
        for (IItemPedido item : pedidoAtual) {
            System.out.println("- " + item.getDescricao() + " (R$ " + item.getPreco() + ")");
            total += item.getPreco();
        }
        System.out.println("TOTAL A PAGAR: R$ " + String.format("%.2f", total));

        // 2. Escolher Pagamento
        IMetodoPagamento metodoPag = handleMenuPagamento();
        if (metodoPag == null) {
            System.out.println("Pagamento cancelado. Pedido não finalizado.");
            return;
        }

        // 3. Processar Pagamento (DI por Método)
        // O Terminal DELEGA o processamento para o PaymentProcessor,
        // INJETANDO o método de pagamento escolhido.
        paymentProcessor.processar(total, metodoPag);

        // 4. Enviar para a Fila (Singleton)
        System.out.println("Enviando pedido para a cozinha/barista...");
        for (IItemPedido item : pedidoAtual) {
            // USA O SINGLETON: Adiciona o item na fila única
            filaDePedidos.adicionarPedido(item);
        }

        System.out.println("------------------------------------------");
        System.out.println("Pedido finalizado com sucesso! Retire no balcão.");
        System.out.println("Total de pedidos na fila: " + filaDePedidos.getTamanhoFila());
        System.out.println("------------------------------------------");
    }

    private IMetodoPagamento handleMenuPagamento() {
        while(true) {
            System.out.println("\n-- Método de Pagamento --");
            System.out.println("1. PIX");
            System.out.println("2. Cartão (Débito/Crédito)");
            System.out.println("3. Cancelar Pagamento");
            System.out.print("Escolha: ");
            int escolha = input.lerInt();

            try {
                if (escolha == 1) {
                    // USA A FÁBRICA: Pede um método de pagamento
                    return pagamentoFactory.criarMetodoPagamento("PIX");
                } else if (escolha == 2) {
                    // USA A FÁBRICA: Pede um método de pagamento
                    return pagamentoFactory.criarMetodoPagamento("CARTAO");
                } else if (escolha == 3) {
                    return null; // Cancela
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void exibirCarrinho() {
        if (pedidoAtual.isEmpty()) {
            System.out.println("(Carrinho vazio)");
            return;
        }

        double total = pedidoAtual.stream()
                .mapToDouble(IItemPedido::getPreco)
                .sum();

        System.out.println("Itens no carrinho: " + pedidoAtual.size() + " | Total parcial: R$ " + String.format("%.2f", total));
    }
}