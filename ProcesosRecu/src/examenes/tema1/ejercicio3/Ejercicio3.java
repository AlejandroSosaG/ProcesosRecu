package examenes.tema1.ejercicio3;

import java.io.*;
import java.util.*;
public class Ejercicio3 {
    public static void main(String[] args) {
        // Declarmaos las varaibles.
        int numero = 1;    // Variable que nos servirá para contar el numero de procesos.
        String [] comando1 = {"Java", "ProcesosRecu/src/examenes/tema1/ejercicio3/NumerosAleatorios.java"};
        List<Process> listaProcesos = new ArrayList<>();
        ProcessBuilder pb1 = new ProcessBuilder(comando1);
        pb1.redirectError(ProcessBuilder.Redirect.INHERIT);
        for (int i = 0; i < 10; i++) {
            pb1.redirectOutput(new File("ProcesosRecu/src/examenes/tema1/ejercicio3/random"+i+".txt"));
            Process p = null;
            try {
                p = pb1.start();
            } catch (IOException e) {
                System.err.println("Error durante la ejecución del proceso");
                System.err.println(e.getLocalizedMessage());
                e.printStackTrace();
            }
            listaProcesos.add(p);
            numero++;
        }
        while (numero > 0) {
            numero = 10;
            for (Process proceso : listaProcesos) {
                if(!proceso.isAlive()) {
                    numero--;
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            String numFichero = String.valueOf(i);
            String [] comando2 = {"java", "ProcesosRecu/src/examenes/tema1/ejercicio3/SumaNumeros.java", numFichero};
            String [] comando3 = {"java", "ProcesosRecu/src/examenes/tema1/ejercicio3/MediaNumeros.java", numFichero};
            ProcessBuilder pb2 = new ProcessBuilder(comando2);
            ProcessBuilder pb3 = new ProcessBuilder(comando3);
            pb2.redirectInput(new File("ProcesosRecu/src/examenes/tema1/ejercicio3/random" + i + ".txt"));
            pb2.redirectOutput(ProcessBuilder.Redirect.appendTo(new File("ProcesosRecu/src/examenes/tema1/ejercicio3/sumas.txt")));
            pb3.redirectInput(new File("ProcesosRecu/src/examenes/tema1/ejercicio3/random" + i + ".txt"));
            pb3.redirectOutput(ProcessBuilder.Redirect.appendTo(new File("ProcesosRecu/src/examenes/tema1/ejercicio3/medias.txt")));
            try {
                pb2.start();
                pb3.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("El proceso ha finalizado correctamente");
    }
}