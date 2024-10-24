import java.util.ArrayList;
import java.util.Scanner;

class Celula<T> {
    T elemento;
    Celula<T> proximo;

    public Celula(T elemento) {
        this.elemento = elemento;
        this.proximo = null;
    }
}

class Pilha<E> {
    private Celula<E> topo;

    public Pilha() {
        topo = null;
    }

    public void empilhar(E elemento) {
        Celula<E> nova = new Celula<>(elemento);
        nova.proximo = topo;
        topo = nova;
    }

    public E desempilhar() {
        if (topo == null) {
            return null;
        }
        E elemento = topo.elemento;
        topo = topo.proximo;
        return elemento;
    }

    public boolean estaVazia() {
        return topo == null;
    }
}

public class Main {

    public static boolean bemounaoformada(String expressao) {
        Pilha<Character> pilha = new Pilha<>();

        for (int i = 0; i < expressao.length(); i++) {
            char caractere = expressao.charAt(i);
            if (caractere == '(' || caractere == '[') {
                pilha.empilhar(caractere);
            }
            else if (caractere == ')') {
                if (pilha.estaVazia() || pilha.desempilhar() != '(') {
                    return false;
                }
            }
            else if (caractere == ']') {
                if (pilha.estaVazia() || pilha.desempilhar() != '[') {
                    return false;
                }
            }
        }
        return pilha.estaVazia();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> expressoes = new ArrayList<>();
        String expressao;
        while (true) {
            expressao = scanner.nextLine();
            if (expressao.equals("FIM")) {
                break;
            }
            expressoes.add(expressao);
        }
        for (String exp : expressoes) {
            if (bemounaoformada(exp)) {
                System.out.println("correto");
            } else {
                System.out.println("incorreto");
            }
        }
        scanner.close();
    }
}
