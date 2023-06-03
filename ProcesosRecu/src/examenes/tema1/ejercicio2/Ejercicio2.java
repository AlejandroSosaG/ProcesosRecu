package examenes.tema1.ejercicio2;

import java.io.File;
import java.io.IOException;

public class Ejercicio2 {
    public static void main(String[] args) {
        String[] comando = {"java", "ProcesosRecu/src/examenes/tema1/ejercicio2/MayoresEdad.java", "ProcesosRecu/src/examenes/tema1/ejercicio2/alumnos.txt"};
        String[] comando2 = {"java", "ProcesosRecu/src/examenes/tema1/ejercicio2/OrdenaNombres.java"};
        // Creamos el lanzador con el comando que le pasamos por pantalla.
        ProcessBuilder pb = new ProcessBuilder(comando);
        ProcessBuilder pb2 = new ProcessBuilder(comando2);
        // Le decimo que muestre la entrada, salida y error por pantalla.
        pb.inheritIO();
        pb2.redirectInput(ProcessBuilder.Redirect.INHERIT);
        pb2.redirectOutput(new File("ProcesosRecu/src/examenes/tema1/ejercicio2/alumnos_mayores.txt"));
        try {
            // Lanzamos el proceso.
            Process p = pb.start();
            Process p2 = pb2.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}