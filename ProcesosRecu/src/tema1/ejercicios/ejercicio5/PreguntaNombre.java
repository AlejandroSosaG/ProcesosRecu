package tema1.ejercicios.ejercicio5;

import java.util.Scanner;

public class PreguntaNombre {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nombre = sc.nextLine();
        System.out.println("Hola " + nombre);
        sc.close();
    }
}
