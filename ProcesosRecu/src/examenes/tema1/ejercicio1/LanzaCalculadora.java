package examenes.tema1.ejercicio1;

import java.io.IOException;
import java.util.Scanner;

public class LanzaCalculadora {
    public static void main(String[] args) {
        int opc = 0, num1, num2;
        Scanner sc = new Scanner(System.in);
        System.out.println("Elija la operación a realizar:\n" +
                "1. SUMA\n" +
                "2. RESTA\n" +
                "3. MULTIPLICACIÓN\n" +
                "4. DIVISIÓN");
        opc = sc.nextInt();
        sc.nextLine();
        System.out.println("Introduzca el primer número");
        num1 = sc.nextInt();
        sc.nextLine();
        System.out.println("Introduzca el segundo número");
        num2 = sc.nextInt();
        sc.nextLine();
        lanzaCalculadora(opc, num1, num2);
    }

    public static void lanzaCalculadora(int opc, int a, int b) {
        String[] comando = {"java", "ProcesosRecu/src/examenes/tema1/ejercicio1/Calculadora.java", String.valueOf(opc), String.valueOf(a), String.valueOf(b)};
        // Creamos el lanzador con el comando que le pasamos por pantalla.
        ProcessBuilder pb = new ProcessBuilder(comando);
        // Le decimo que muestre la entrada, salida y error por pantalla.
        pb.inheritIO();
        try {
            // Lanzamos el proceso.
            Process p = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}