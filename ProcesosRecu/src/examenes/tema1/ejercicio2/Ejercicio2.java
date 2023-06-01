package examenes.tema1.ejercicio2;

import java.io.IOException;

public class Ejercicio2 {
    public static void main(String[] args) {
        String[] comando = {"java", "ProcesosRecu/src/examenes/tema1/ejercicio2/OrdenaNombres.java", "ProcesosRecu/src/examenes/tema1/ejercicio2/alumnos.txt"};
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