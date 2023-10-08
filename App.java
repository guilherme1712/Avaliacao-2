// Guilherme de Quadro Daudt e João Pedro Fonseca


// Classe que representa a Árvore Binária

import java.util.Scanner;

class ArvoreBinaria {
    Node raiz;     // Raiz da árvore

    public ArvoreBinaria() {
        raiz = null;          // Inicialmente, a árvore está vazia
    }

    // Método para inserir um elemento em uma árvore binária de busca
    public void inserir(int valor) {
        raiz = inserirRec(raiz, valor);
    }

    private Node inserirRec(Node no, int valor) {
        // Se a árvore estiver vazia ou chegarmos a uma folha, criamos um novo nó
        if (no == null) {
            no = new Node(valor);
            return no;
        }

        // Caso contrário, percorremos a árvore para a esquerda ou direita, dependendo do valor
        if (valor < no.chave) {
            no.esquerda = inserirRec(no.esquerda, valor);
        } else if (valor > no.chave) {
            no.direita = inserirRec(no.direita, valor);
        }

        return no;
    }

    // Método para percorrer a árvore binária nas formas pré-ordem, inordem e pós-ordem
    public void percorrer(int escolha) {
        switch (escolha) {
            case 1:
                System.out.println("Percorrendo em pré-ordem:");
                preOrdem(raiz);
                break;
            case 2:
                System.out.println("Percorrendo em ordem (inordem):");
                emOrdem(raiz);
                break;
            case 3:
                System.out.println("Percorrendo em pós-ordem:");
                posOrdem(raiz);
                break;
            default:
                System.out.println("Opção inválida");
        }
    }

    // Método para percorrer a árvore em pré-ordem
    private void preOrdem(Node no) {
        if (no != null) {
            System.out.print(no.chave + " ");
            preOrdem(no.esquerda);
            preOrdem(no.direita);
        }
    }

    // Método para percorrer a árvore em ordem (inordem)
    private void emOrdem(Node no) {
        if (no != null) {
            emOrdem(no.esquerda);
            System.out.print(no.chave + " ");
            emOrdem(no.direita);
        }
    }

    // Método para percorrer a árvore em pós-ordem
    private void posOrdem(Node no) {
        if (no != null) {
            posOrdem(no.esquerda);
            posOrdem(no.direita);
            System.out.print(no.chave + " ");
        }
    }

    // Método para remover o maior elemento da árvore
    public void removerMaior() {
        raiz = removerMaiorRec(raiz);
    }

    private Node removerMaiorRec(Node no) {
        if (no == null) {
            return no;
        }

        // Se o maior elemento estiver na subárvore direita, removemos recursivamente
        if (no.direita == null) {
            return no.esquerda;
        }

        no.direita = removerMaiorRec(no.direita);
        return no;
    }

    // Método para remover o menor elemento da árvore
    public void removerMenor() {
        raiz = removerMenorRec(raiz);
    }

    private Node removerMenorRec(Node no) {
        if (no == null) {
            return no;
        }

        // Se o menor elemento estiver na subárvore esquerda, removemos recursivamente
        if (no.esquerda == null) {
            return no.direita;
        }

        no.esquerda = removerMenorRec(no.esquerda);
        return no;
    }

    // Método para remover um elemento específico da árvore
    public void remover(int valor) {
        raiz = removerRec(raiz, valor);
    }

    private Node removerRec(Node no, int valor) {
        if (no == null) {
            return no;
        }

        // Caso contrário, percorremos a árvore para encontrar o nó a ser removido
        if (valor < no.chave) {
            no.esquerda = removerRec(no.esquerda, valor);
        } else if (valor > no.chave) {
            no.direita = removerRec(no.direita, valor);
        } else {
            // Se encontrarmos o nó a ser removido, realizamos a remoção
            if (no.esquerda == null) {
                return no.direita;
            } else if (no.direita == null) {
                return no.esquerda;
            }

            // Encontramos o nó com valor mínimo na subárvore direita para substituição
            no.chave = valorMinimo(no.direita);

            // Removemos o nó com o valor mínimo da subárvore direita
            no.direita = removerRec(no.direita, no.chave);
        }

        return no;
    }

    // Método auxiliar para encontrar o valor mínimo em uma árvore
    private int valorMinimo(Node no) {
        int valorMinimo = no.chave;
        while (no.esquerda != null) {
            valorMinimo = no.esquerda.chave;
            no = no.esquerda;
        }
        return valorMinimo;
    }
    
    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();  // Criação de uma árvore binária
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEscolha uma operação:");
            System.out.println("1. Inserir elemento");
            System.out.println("2. Percorrer a árvore");
            System.out.println("3. Remover o maior elemento");
            System.out.println("4. Remover o menor elemento");
            System.out.println("5. Remover um elemento específico");
            System.out.println("6. Sair");

            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    System.out.print("Digite o elemento a ser inserido: ");
                    int elemento = scanner.nextInt();
                    arvore.inserir(elemento);
                    break;
                case 2:
                    System.out.println("Escolha a ordem de percorrimento:");
                    System.out.println("1. Pré-ordem");
                    System.out.println("2. Inordem");
                    System.out.println("3. Pós-ordem");
                    int escolhaPercorrido = scanner.nextInt();
                    arvore.percorrer(escolhaPercorrido);
                    break;
                case 3:
                    arvore.removerMaior();
                    System.out.println("Maior elemento removido.");
                    break;
                case 4:
                    arvore.removerMenor();
                    System.out.println("Menor elemento removido.");
                    break;
                case 5:
                    System.out.print("Digite o elemento a ser removido: ");
                    int elementoARemover = scanner.nextInt();
                    arvore.remover(elementoARemover);
                    System.out.println("Elemento removido.");
                    break;
                case 6:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}