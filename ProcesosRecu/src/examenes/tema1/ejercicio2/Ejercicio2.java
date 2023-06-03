package examenes.tema1.ejercicio2;

import java.io.*;
import java.util.*;

public class Ejercicio2 {
    public static void main(String[] args) {
        String[] comando = {"java", "ProcesosRecu/src/examenes/tema1/ejercicio2/MayoresEdad.java"};
        String[] comando2 = {"java", "ProcesosRecu/src/examenes/tema1/ejercicio2/OrdenaNombres.java"};
        // Creamos los lanzadores con los comandos que les pasamos.
        ProcessBuilder pb = new ProcessBuilder(comando);
        ProcessBuilder pb2 = new ProcessBuilder(comando2);
        List<ProcessBuilder> lpb = new ArrayList<>();
        lpb.add(pb);
        lpb.add(pb2);
        // Le decimos que coja la entrada del primer proceso del fichero.
        pb.redirectInput(new File("ProcesosRecu/src/examenes/tema1/ejercicio2/alumnos.txt"));
        // Ponemos la salida del segundo proceso en el fichero escrito.
        pb2.redirectOutput(new File("ProcesosRecu/src/examenes/tema1/ejercicio2/alumnos_mayores.txt"));
        try {
            // Lanzamos los procesos.
            ProcessBuilder.startPipeline(lpb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}