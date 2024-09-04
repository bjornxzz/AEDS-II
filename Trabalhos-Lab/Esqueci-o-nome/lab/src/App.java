import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String linha = sc.nextLine();

            if (linha.equals("FIM")) {
                break;
            }

            String[] numeroS = linha.split(";");
            int[] A = new int[numeroS.length];

            for (int i = 0; i < numeroS.length; i++) {
                A[i] = Integer.parseInt(numeroS[i]);
            }

            int comparacoes = selectionSort(A);

            for (int i = 0; i < A.length; i++) {
                System.out.print(A[i] + (i < A.length - 1 ? "\t" : ""));
            }
            System.out.println();

            System.out.println("Comparacoes realizadas: " + comparacoes);
        }

        sc.close();
    }

    public static int selectionSort(int[] array) {
        int comparacoes = 0;

        for (int i = 0; i < array.length - 1; i++) {
            int menor = i;
            for (int j = i + 1; j < array.length; j++) {
                comparacoes++; 
                if (array[j] < array[menor]) {
                    menor = j;
                }
            }

            if (menor != i) {
                int temp = array[i];
                array[i] = array[menor];
                array[menor] = temp;
            }
        }

        return comparacoes;
    }
}