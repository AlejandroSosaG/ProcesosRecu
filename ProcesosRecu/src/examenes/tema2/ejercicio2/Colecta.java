package examenes.tema2.ejercicio2;

import java.util.Random;

public class Colecta {
    static int cant = 0;
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i += 3) {
            Thread hilo = new Thread(new Recolector());
            hilo.setName("recolector " + i);
            hilo.setPriority(i);
            hilo.start();
        }
        for (int i = 1; i <= 4; i++) {
            Thread hilo = new Thread(new Consumidor());
            hilo.setName("consumidor " + i);
            hilo.start();
        }
    }
}
