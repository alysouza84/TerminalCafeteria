# Terminal de Auto-Atendimento: Cafeteria (TerminalCafeteria)

Este é um projeto de simulação de um terminal de auto-atendimento para uma cafeteria, desenvolvido em Java. O objetivo principal é aplicar e demonstrar o uso prático de diversos Padrões de Projeto (Design Patterns) e princípios de design, como SOLID, em um cenário de negócios do mundo real.

O usuário pode escolher cafés (Espresso, Filtrado), personalizá-los com adicionais (Açúcar, Leite), adicionar comidas (Coxinha, Croissant, Donuts) e, por fim, pagar pelo pedido usando diferentes métodos.

---

## 🚀 Tecnologias Utilizadas

* **Java** (linguagem principal, sem frameworks externos)
* **Git & GitHub** (controle de versão)

---

## ⚙️ Como Executar o Projeto

Para clonar e executar este projeto localmente, você precisará ter o **Java Development Kit (JDK)** (versão 8 ou superior) e o **Git** instalados no seu computador.

### 1. Clonar o Repositório

Abra seu terminal (Terminal, Git Bash, etc.) e clone o repositório usando o seguinte comando:

```bash
git clone [https://github.com/alysouza84/TerminalCafeteria.git](https://github.com/alysouza84/TerminalCafeteria.git)

2. Navegar para a Pasta
Entre na pasta do projeto que acabou de ser criada:

Bash

cd TerminalCafeteria
3. Compilar o Projeto
O projeto usa uma estrutura de pacotes (com.cafeteria...). Para compilar corretamente, você precisa executar o comando javac a partir da pasta raiz do projeto (TerminalCafeteria).

Primeiro, crie um diretório para os arquivos compilados (.class):

Bash

mkdir bin
Em seguida, compile todo o código-fonte. O comando a seguir diz ao javac para salvar as classes compiladas na pasta bin, lendo os fontes da pasta src:

Bash

# (Estando na pasta raiz 'TerminalCafeteria')
javac -d bin -cp src src/com/cafeteria/CafeteriaApp.java
(O javac é inteligente o suficiente para encontrar e compilar todas as outras classes que CafeteriaApp.java depende).

4. Executar o Aplicativo
Agora que o código está compilado na pasta bin, você pode executá-lo. Use o comando java, especificando o classpath (-cp) para a pasta bin e o nome completo da classe principal (com o pacote).

Bash

java -cp bin com.cafeteria.CafeteriaApp
Isso iniciará o menu interativo do terminal no seu console.

💡 Padrões de Projeto e Princípios Implementados
Este projeto foi estruturado para ser um exemplo prático de Padrões de Projeto. Aqui está onde encontrar cada um:

1. Princípio da Responsabilidade Única (SRP - SOLID)
O SRP é o princípio que guia a arquitetura. Em vez de uma classe Terminal monolítica, as responsabilidades são divididas:

terminal/TerminalKiosk.java: Apenas orquestra o fluxo do menu e a interação com o usuário.

services/factories/*.java: As fábricas (ex: BebidaFactory) têm a única responsabilidade de criar objetos.

services/processing/PaymentProcessor.java: Tem a única responsabilidade de processar pagamentos.

services/queue/FilaDePedidos.java: Tem a única responsabilidade de gerenciar a fila.

2. Injeção de Dependência (DI)
A DI é usada para desacoplar as classes, permitindo que o TerminalKiosk funcione com qualquer fábrica ou processador que siga a interface.

Injeção por Construtor (Principal):

Arquivo: terminal/TerminalKiosk.java

Explicação: O TerminalKiosk não cria suas próprias "ferramentas" (as fábricas, o processador, a fila). Ele os recebe prontos em seu construtor. Isso o torna totalmente desacoplado.

O "Injetor" (Quem fornece as dependências):

Arquivo: CafeteriaApp.java (o main)

Explicação: O main é responsável por criar (new) todas as dependências e injetá-las no TerminalKiosk no momento da criação.

Injeção por Método:

Arquivo: services/processing/PaymentProcessor.java

Explicação: O método processar(valor, metodo) recebe a dependência IMetodoPagamento como um parâmetro. Isso é ideal, pois o método de pagamento (PIX, Cartão) muda a cada transação.

3. Padrão Singleton
O Singleton é usado para garantir que exista apenas uma instância de uma classe em toda a aplicação.

Arquivo: services/queue/FilaDePedidos.java

Explicação: A fila de pedidos deve ser única para toda a cafeteria. Todos os terminais (mesmo que tivéssemos vários) e todos os baristas devem interagir com a mesma fila. O Singleton (com seu construtor privado e método estático getInstancia()) garante isso. O CafeteriaApp.java pega essa instância e a injeta no terminal.

4. Padrão Factory (Simple Factory)
O Factory é usado para centralizar e esconder a lógica de criação de objetos.

Arquivos: Pacote services/factories/

BebidaFactory.java

ComidaFactory.java

PagamentoFactory.java

Explicação: O TerminalKiosk não sabe como "construir" um Espresso ou um PagamentoPix (ele não usa new Espresso()). Ele simplesmente pede à fábrica apropriada: bebidaFactory.criarBebida("ESPRESSO"). Isso desacopla o terminal das classes concretas dos produtos e pagamentos.

5. Padrão Decorator
O Decorator é usado para adicionar "extras" (funcionalidades/comportamentos) a um objeto dinamicamente (em tempo de execução), sem precisar alterar sua classe.

Arquivos: Pacote domain/decorators/

BebidaDecorator.java (a classe abstrata)

AdicionalAcucar.java (um decorador concreto)

AdicionalLeite.java (outro decorador concreto)

Explicação: Em vez de criar classes como EspressoComLeite, EspressoComAcucar, EspressoComLeiteEAcucar, etc., nós "embrulhamos" (decoramos) o objeto IBebida original.

Exemplo no Código: O método handleMenuAdicionais no TerminalKiosk.java aplica os decoradores dinamicamente. Se o usuário pede um Espresso com Leite e depois Açúcar, o objeto final é: new AdicionalAcucar(new AdicionalLeite(new Espresso())).
