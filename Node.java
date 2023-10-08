// Guilherme de Quadro Daudt e João Pedro Fonseca

public class Node { // Classe Node
    int chave;
    Node esquerda;
    Node direita;
    int altura;

    public Node(int chave) {
        this.chave = chave;
        this.esquerda = null;
        this.direita = null;
        this.altura = 1;
    }
}