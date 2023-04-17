package tema2.tarea3;

import java.util.*;
public class GestorHojas extends Thread {
    private static List<String> lista = new ArrayList<>();
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            lista.add(new String("Texto " + i));
        }
        for (String string : lista) {
            System.out.println(string);
        }
    }
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            GestorHojas gestor = new GestorHojas();
            gestor.run();
        }

    }
}
    