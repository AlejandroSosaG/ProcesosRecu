package tema2.tarea3;

import java.util.*;
public class GestorHojas extends Thread {
    // Creación de una lista de tipo cadena.
    private static List<String> lista = new ArrayList<>();
    @Override
    public void run() {
        // Recorremos el bucle 10 veces para llenar la lista de 10 cadenas.
        for (int i = 0; i < 10; i++) {
            lista.add(new String("Texto " + i));
        }
        // Recorrems la lista y mostramos su contenido.
        for (String string : lista) {
            System.out.println(string);
        }
    }
    public static void main(String[] args) {
        // Recorremos  el bucle 10 veces creando un hilo y lanzandolo cada vez.
        for (int i = 0; i < 10; i++) {
            GestorHojas gestor = new GestorHojas();
            gestor.start();
        }
    }
}
    