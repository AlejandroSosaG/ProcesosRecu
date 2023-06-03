package examenes.tema1.ejercicio3;

import java.io.File;
import java.io.IOException;

public class Ejercicio3 {
    public static void main(String[] args) {
        // Creación de comandos y lanzadores.
        String[] comando = {"java", "ProcesosRecu/src/examenes/tema1/ejercicio3/NumerosAleatorios.java"};
        ProcessBuilder pb = new ProcessBuilder(comando);
        String[] comando2 = {"java", "ProcesosRecu/src/examenes/tema1/ejercicio3/SumaNumeros.java"};
        ProcessBuilder pb2 = new ProcessBuilder(comando2);
        String[] comando3 = {"java", "ProcesosRecu/src/examenes/tema1/ejercicio3/MediaNumeros.java"};
        ProcessBuilder pb3 = new ProcessBuilder(comando3);
        try {
            /**
             * Recorremos el bucle 10 veces.
             */
            for (int i = 0; i < 11; i++) {
                // Ponemos la salida del primer proceso en el archivo escrito.
                pb.redirectOutput(new File("ProcesosRecu/src/examenes/tema1/ejercicio3/random" + i + ".txt"));
                // Ponemos la entrada del segundo proceso en el archivo escrito.
                pb2.redirectInput(new File("ProcesosRecu/src/examenes/tema1/ejercicio3/random" + i + ".txt"));
                // Ponemos la salida del segundo proceso en el archivo escrito sin borrar lo que hay en él.
                pb2.redirectOutput(ProcessBuilder.Redirect.appendTo(new File("ProcesosRecu/src/examenes/tema1/ejercicio3/sumas.txt")));
                // Ponemos la entrada del tercer proceso en el archivo escrito.
                pb3.redirectInput(new File("ProcesosRecu/src/examenes/tema1/ejercicio3/random" + i + ".txt"));
                // Ponemos la salida del tercer proceso en el archivo escrito sin borrar lo que hay en él.
                pb3.redirectOutput(ProcessBuilder.Redirect.appendTo(new File("ProcesosRecu/src/examenes/tema1/ejercicio3/medias.txt")));
                // Lanzamos los procesos esperando a que termine de ejecutarse el primero.
                Process p = pb.start();
                p.waitFor();
                Process p2 = pb2.start();
                Process p3 = pb3.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
