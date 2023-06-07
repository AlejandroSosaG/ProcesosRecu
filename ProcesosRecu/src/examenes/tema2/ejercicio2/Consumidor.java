package examenes.tema2.ejercicio2;

import java.util.Random;

public class Consumidor implements Runnable {
    @Override
    public void run() {
        int colecta;
        Random r = new Random();
        Colecta c = new Colecta();
        try {
            while (true) {
                colecta = r.nextInt(10, 40);
                if (colecta <= c.cant) {
                    c.cant -= colecta;
                    System.out.println("El " + Thread.currentThread().getName() + " recoje " + colecta);
                    System.out.println("Cantidad actual = " + c.cant);
                }
                Thread.sleep(r.nextLong(20, 300));
            }
        } catch (InterruptedException e) {
            System.err.println("Ha ocurrido un error con la espera");
            e.printStackTrace();
        }
    }
}
