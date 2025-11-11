package com.cafeteria.services.queue;

import com.cafeteria.domain.items.IItemPedido;
import java.util.Queue;
import java.util.LinkedList;

/**
 * PADRÃO DE PROJETO: Singleton
 *
 * Esta classe GARANTE que só existirá UMA ÚNICA instância
 * dela em toda a aplicação.
 *
 * Isso é CRUCIAL: Todos os terminais (TerminalKiosk)
 * precisam adicionar pedidos na MESMA fila, e os Baristas
 * precisam ler dessa MESMA fila.
 */
public class FilaDePedidos {

    // 2. A Instância ÚNICA estática e privada
    // (Começa como null)
    private static FilaDePedidos instanciaUnica;

    // O recurso que o Singleton está a proteger: a fila
    private Queue<IItemPedido> pedidos;

    // 1. O Construtor PRIVADO
    // Isso impede que qualquer outra classe use 'new FilaDePedidos()'
    private FilaDePedidos() {
        // Usamos LinkedList como uma implementação de Fila (Queue)
        pedidos = new LinkedList<>();
    }

    // 3. O Método de Acesso Global (a "porta de entrada")
    // É 'static' para que possamos chamar: FilaDePedidos.getInstancia()
    public static FilaDePedidos getInstancia() {

        // "Lazy Loading" (Inicialização Preguiçosa)
        // Só cria a instância na PRIMEIRA vez que for pedida.
        if (instanciaUnica == null) {
            System.out.println("LOG: Criando a instância ÚNICA da Fila de Pedidos.");
            instanciaUnica = new FilaDePedidos();
        }

        // Retorna a instância (nova ou a já existente)
        return instanciaUnica;
    }

    // --- Métodos de Negócio ---

    /**
     * Adiciona um item (ou lista de itens) à fila única
     */
    public void adicionarPedido(IItemPedido item) {
        pedidos.add(item);
        System.out.println("FILA [SINGLETON]: Pedido adicionado (" + item.getDescricao() + ")");
    }

    /**
     * Método que o Barista usaria para pegar o próximo pedido
     */
    public IItemPedido pegarProximoPedido() {
        // .poll() remove e retorna o primeiro item, ou null se a fila estiver vazia
        return pedidos.poll();
    }

    public int getTamanhoFila() {
        return pedidos.size();
    }
}