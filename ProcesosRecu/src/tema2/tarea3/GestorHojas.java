package tema2.tarea3;

import java.util.*;
public class GestorHojas extends Thread {
    // Creación de una lista de tipo cadena.
    private static List<String> lista = new ArrayList<>();
    private static List<String> list = Collections.synchronizedList(lista);
    @Override
    public void run() {
        // Recorremos el bucle 10 veces para llenar la lista de 10 cadenas.
        for (int i = 0; i < 10; i++) {
            list.add(new String("Texto " + i));
        }
        synchronized (list){
            for (String i:list) {
                System.out.println(i);
            }
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
