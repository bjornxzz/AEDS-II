import java.util.Scanner;

class Pilha {
    Celula topo;
    
    Pilha() {
        this.topo = null;
    }
    
    char desempilhar() {
        if (topo == null) {
            throw new IllegalStateException("A pilha está vazia");
        }
        char dado = topo.dado;
        topo = topo.proximo;
        return dado;
    }
    
    void empilhar(char dado) {
        Celula novaCelula = new Celula(dado);
        novaCelula.proximo = topo;
        topo = novaCelula;
    }
    
    boolean vazia() {
        return topo == null;
    }
    char topo() {
        if (topo == null) {
            throw new IllegalStateException("A pilha está vazia");
        }
        return topo.dado;
    }
}

class Celula {
    char dado;
    Celula proximo;

    Celula(char dado) {
        this.dado = dado;
        this.proximo = null;
    }
}
public class NotacaoPolonesaReversa {
    public static String converterParaNPR(String expressao) {
        StringBuilder resultado = new StringBuilder();
        Pilha pilha = new Pilha();
        int numParentesesAbertos = 0;
        
        for (int i = 0; i < expressao.length(); i++) {
            char caractere = expressao.charAt(i);
            
            if (Character.isWhitespace(caractere)) {
                continue; // Ignora espaços em branco
            } else if (Character.isLetterOrDigit(caractere)) {
                resultado.append(caractere).append(" ");
            } else if (caractere == '(') {
                pilha.empilhar(caractere);
                numParentesesAbertos++;
            } else if (caractere == ')') {
                if (numParentesesAbertos == 0) {
                    throw new IllegalArgumentException("Expressão inválida: parênteses desbalanceados");
                }
                while (!pilha.vazia() && pilha.topo() != '(') {
                    resultado.append(pilha.desempilhar()).append(" ");
                }
                if (!pilha.vazia() && pilha.topo() == '(') {
                    pilha.desempilhar(); // Remove o '('
                } else {
                    throw new IllegalArgumentException("Expressão inválida: parênteses desbalanceados");
                }
                numParentesesAbertos--;
            } else {
                // operador encontrado
                while (!pilha.vazia() && precedencia(pilha.topo()) >= precedencia(caractere)) {
                    resultado.append(pilha.desempilhar()).append(" ");
                }
                pilha.empilhar(caractere);
            }
        }

        
        while (!pilha.vazia()) {
            char topo = pilha.desempilhar();
            if (topo == '(' || topo == ')') {
                throw new IllegalArgumentException("Expressão inválida: parênteses desbalanceados");
            }
            resultado.append(topo).append(" ");
        }
        
        if (numParentesesAbertos != 0) {
            throw new IllegalArgumentException("Expressão inválida: parênteses desbalanceados");
        }
        return resultado.toString().trim(); 
    }

    private static int precedencia(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expressao;

        while (!(expressao = scanner.nextLine()).equals("FIM")) {
            try {
                String npr = converterParaNPR(expressao);
                System.out.println(npr);
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
