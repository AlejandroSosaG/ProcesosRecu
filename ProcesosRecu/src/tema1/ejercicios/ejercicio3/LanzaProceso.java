package tema1.ejercicios.ejercicio3;

import java.io.IOException;
import java.util.Scanner;

public class LanzaProceso {
    public static void main(String[] args) {
        String programa; // En esta variable guardaremos el nombre del proceso que queremos lanzar.
        // Creación de escaner.
        Scanner sc = new Scanner(System.in);
        //  Le pedimos al usuario que escriba el nombre del programa a lanzar.
        System.out.println("Introduzca el nombre del programa a lanzar");
        programa = sc.nextLine();
        String comando[] = {"java", programa};
        // Creamos el lanzador que utilizaremos.
        ProcessBuilder pb = new ProcessBuilder();
        // Le decimos que muestre la entrada, salida y error por pantalla.
        pb.inheritIO();
        try {
            // Lanzamos el proceso.
            pb.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
