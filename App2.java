// Guilherme de Quadro Daudt e João Pedro Fonseca

import java.util.Random; // Importação de random.
import java.util.Scanner; // Importação da classe Scanner.

public class App2 {

    private Node raiz; // Declaração de uma variável de instância Node chamada raiz.
    private int seedGeral; // Declaração de uma variável de instância inteira chamada seedGeral.

    public App2() {
        this.raiz = null; // Inicializa a raiz como nula.
        this.seedGeral = 0; // Inicializa a seedGeral como 0.
    }

    // Função para inserir um valor na árvore AVL.
    public void inserir(int chave) {
        raiz = inserirRec(raiz, chave); // Chama a função inserirRec com a raiz e a chave como parâmetros e atualiza a raiz.
    }

    // Função auxiliar para inserir um valor na árvore AVL de forma recursiva.
    private Node inserirRec(Node raiz, int chave) {
        if (raiz == null) {
            return new Node(chave); // Cria um novo nó com a chave se a raiz for nula.
        }

        if (chave < raiz.chave) {
            raiz.esquerda = inserirRec(raiz.esquerda, chave); // Insere na subárvore esquerda se a chave for menor.
        } else if (chave > raiz.chave) {
            raiz.direita = inserirRec(raiz.direita, chave); // Insere na subárvore direita se a chave for maior.
        } else {
            // Chave duplicada não é permitida
            return raiz; // Retorna a raiz sem alterações se a chave já existe.
        }

        raiz.altura = 1 + Math.max(altura(raiz.esquerda), altura(raiz.direita)); // Atualiza a altura do nó atual.

        int balance = fatorBalanceamento(raiz); // Calcula o fator de balanceamento do nó.

        // Casos de rotação para manter a propriedade de AVL
        if (balance > 1) {
            if (chave < raiz.esquerda.chave) {
                return rotacaoDireita(raiz);
            } else {
                raiz.esquerda = rotacaoEsquerda(raiz.esquerda);
                return rotacaoDireita(raiz);
            }
        }

        if (balance < -1) {
            if (chave > raiz.direita.chave) {
                return rotacaoEsquerda(raiz);
            } else {
                raiz.direita = rotacaoDireita(raiz.direita);
                return rotacaoEsquerda(raiz);
            }
        }

        return raiz; // Retorna a raiz.
    }

    // Função para buscar um valor na árvore AVL
    public boolean buscar(int chave) {
        return buscarRec(raiz, chave); // Chama a função buscarRec com a raiz e a chave como parâmetros.
    }

    // Função auxiliar para buscar um valor na árvore AVL de forma recursiva
    private boolean buscarRec(Node raiz, int chave) {
        if (raiz == null) {
            return false; // Retorna falso se a raiz for nula, indicando que a chave não foi encontrada.
        }

        if (raiz.chave == chave) {
            return true; // Retorna verdadeiro se a chave for encontrada no nó atual.
        }

        if (chave < raiz.chave) {
            return buscarRec(raiz.esquerda, chave); // Busca na subárvore esquerda se a chave for menor.
        } else {
            return buscarRec(raiz.direita, chave); // Busca na subárvore direita se a chave for maior.
        }
    }

    // Função para remover um valor da árvore AVL
    public void remover(int chave) {
        raiz = removerRec(raiz, chave); // Chama a função removerRec com a raiz e a chave como parâmetros e atualiza a raiz.
    }

    // Função auxiliar para remover um valor da árvore AVL de forma recursiva
    private Node removerRec(Node raiz, int chave) {
        if (raiz == null) {
            return raiz; // Retorna nulo se a raiz for nula.
        }

        if (chave < raiz.chave) {
            raiz.esquerda = removerRec(raiz.esquerda, chave); // Remove na subárvore esquerda se a chave for menor.
        } else if (chave > raiz.chave) {
            raiz.direita = removerRec(raiz.direita, chave); // Remove na subárvore direita se a chave for maior.
        } else {
            // Casos de remoção


        }



        return raiz; // Retorna a raiz.
    }

    // Função para encontrar o valor mínimo na árvo re
    private Node minimoValor(Node raiz) {
        Node atual = raiz;
        while (atual.esquerda != null) {
            atual = atual.esquerda;
        }
        return atual;
    }

    // Função para obter a altura de um nó
    private int altura(Node no) {
        if (no == null) {
            return 0;
        }
        return no.altura;
    }

    // Função para calcular o fator de balanceamento de um nó
    private int fatorBalanceamento(Node no) {
        if (no == null) {
            return 0;
        }
        return altura(no.esquerda) - altura(no.direita);
    }

    // Função para realizar uma rotação à direita
    private Node rotacaoDireita(Node y) {
        Node x = y.esquerda;
        Node T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;

        return x;
    }

    // Função para realizar uma rotação à esquerda
    private Node rotacaoEsquerda(Node x) {
        Node y = x.direita;
        Node T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;

        return y;
    }

    // Adicione este método à sua classe App2
    public void imprimirArvore() {
        imprimirRec(raiz, 0); // Chama a função imprimirRec com a raiz e o nível inicial como parâmetros.
    }

    // Adicione este método auxiliar à sua classe App2
    private void imprimirRec(Node no, int nivel) {
        if (no != null) {
            imprimirRec(no.direita, nivel + 1);
            for (int i = 0; i < nivel; i++) {
                System.out.print("    ");
            }
            System.out.println(no.chave);
            imprimirRec(no.esquerda, nivel + 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Runtime runtime = Runtime.getRuntime();

        App2 arvore = new App2();
        long startTime = 0; // Variável para registrar o tempo de inserção

        while (true) {
            System.out.println("Escolha uma operação:");
            System.out.println("1. Inserir elementos aleatórios com semente");
            System.out.println("2. Buscar");
            System.out.println("3. Remover");
            System.out.println("4. Sair");

            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    System.out.println("Escolha a quantidade de elementos a serem inseridos:");
                    System.out.println("1. 100 elementos");
                    System.out.println("2. 500 elementos");
                    System.out.println("3. 1000 elementos");
                    System.out.println("4. 10.000 elementos");
                    System.out.println("5. 20.000 elementos");
                    int opcaoInsercao = scanner.nextInt();

                    int numElementos = 0;
                    long seed = 45; // Semente aleatória baseada no tempo atual

                    switch (opcaoInsercao) {
                        case 1:
                            numElementos = 100;
                            break;
                        case 2:
                            numElementos = 500;
                            break;
                        case 3:
                            numElementos = 1000;
                            break;
                        case 4:
                            numElementos = 10000;
                            break;
                        case 5:
                            numElementos = 20000;
                            break;
                        default:
                            System.out.println("Opção inválida.");
                            continue;
                    }

                    arvore = new App2(); // Inicializa a variável aqui
                    startTime = System.nanoTime(); // Registro do tempo de inserção

                    Random seededRandom = new Random(seed); // Cria um novo Random com a semente

                    // Insere elementos aleatórios na árvore
                    for (int i = 0; i < numElementos; i++) {
                        int valorInserir = seededRandom.nextInt();
                        arvore.seedGeral = valorInserir;

                        arvore.inserir(valorInserir);
                    }
                    // Imprimir a árvore após a remoção
                    arvore.imprimirArvore();
                    break;

                case 2:
                    if (arvore != null) {
                        System.out.println("Digite o valor a ser buscado:");
                        int valorBuscar = scanner.nextInt();
                        boolean encontrado = arvore.buscar(valorBuscar);
                        if (encontrado) {
                            System.out.println("Valor encontrado na árvore.");
                        } else {
                            System.out.println("Valor não encontrado na árvore.");
                        }
                    } else {
                        System.out.println("A árvore ainda não foi criada. Por favor, escolha a opção 1 primeiro.");
                    }
                    break;

                case 3:
                    if (arvore != null) {
                        System.out.println("Escolha a quantidade de elementos a serem removidos:");
                        System.out.println("1. 100 elementos");
                        System.out.println("2. 500 elementos");
                        System.out.println("3. 1000 elementos");
                        System.out.println("4. 10.000 elementos");
                        System.out.println("5. 20.000 elementos");
                        int opcaoRemocao = scanner.nextInt();

                        int numElementosRemover = 0;
                        switch (opcaoRemocao) {
                            case 1:
                                numElementosRemover = 100;
                                break;
                            case 2:
                                numElementosRemover = 500;
                                break;
                            case 3:
                                numElementosRemover = 1000;
                                break;
                            case 4:
                                numElementosRemover = 10000;
                                break;
                            case 5:
                                numElementosRemover = 20000;
                                break;
                            default:
                                System.out.println("Opção inválida.");
                                continue;
                        }

                        int valorRemover = arvore.seedGeral; // Cria um novo Random com a semente

                        // Remove elementos aleatórios da árvore
                        for (int i = 0; i < numElementosRemover; i++) {
                            arvore.remover(valorRemover);
                        }

                        System.out.println("Elementos removidos com sucesso.");
                    } else {
                        System.out.println("A árvore ainda não foi criada. Por favor, escolha a opção 1 primeiro.");
                    }

                    // Imprimir a árvore após a remoção
                    arvore.imprimirArvore();

                    break;

                case 4:
                    System.out.println("Saindo do programa.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Escolha uma opção válida.");
                    break;
            }

            // Gere o relatório aqui
            if (arvore != null) {
                System.out.println("Relatório:");

                // Tempo de inserção
                long endTime = System.nanoTime();
                long elapsedTime = endTime - startTime;
                System.out.println("Tempo de inserção (nanossegundos): " + elapsedTime);

                // Obtém informações de uso de memória
                long memoriaUsada = runtime.totalMemory() - runtime.freeMemory();
                System.out.println("Memória usada (bytes): " + memoriaUsada);

                // Obtém a porcentagem máxima da CPU
                double cpuUsage = (double) (endTime - startTime) / (double) elapsedTime;
                System.out.println("Porcentagem máxima da CPU: " + (cpuUsage) + "%");
            }
        }
    }
}