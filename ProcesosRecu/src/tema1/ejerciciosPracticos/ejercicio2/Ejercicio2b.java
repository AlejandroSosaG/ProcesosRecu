package ejerciciospracticos.ejercicio02;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
public class Ejercicio2b {
    // Declaramos un fichero que es el que vamos a abrir con el bloque de notas.
    private static final File fichero = new File("ProcesosRecu/src/tema1/ejerciciosPracticos/ejercicio2/numLineas.txt");
    // Declaramos un file carpeta con la ruta donde vamos a guardar el fichero.
    public static void main(String[] args) {
        // Declaramos los comandos que vamos a utilizar.
        String[] comando1 = {"notepad", String.valueOf(fichero)};
        String[] comando2 = {"java", "ProcesosRecu/src/tema1/ejerciciosPracticos/ejercicio2/CuentaLineas.java"};
        // Declaramos 2 ProcessBuilder y les pasamos los dos comandos creados previamente.
        ProcessBuilder pb1 = new ProcessBuilder(comando1);
        ProcessBuilder pb2 = new ProcessBuilder(comando2);
        // Redirijimos la entrada del proceso2 al fichero.
        pb2.redirectInput(ProcessBuilder.Redirect.from(new File("ProcesosRecu/src/tema1/ejerciciosPracticos/ejercicio2/numLineas.txt")));
        // Redirijimos la salida y la salida de error del proceso2 a la consola.
        pb2.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        pb2.redirectError(ProcessBuilder.Redirect.INHERIT);
        pb1.inheritIO();
        try {
            Process p1 = pb1.start();
            if (p1.waitFor(30, TimeUnit.SECONDS)) {
                Process p2 = pb2.start();
                p2.waitFor();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}