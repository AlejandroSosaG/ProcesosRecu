package examenes.tema2.ejercicio2;

import java.util.Random;

public class Recolector implements Runnable {
    @Override
    public void run() {
        int colecta = 0;
        Random r = new Random();
        Colecta c = new Colecta();
        while (c.cant < 2000) {
            colecta = r.nextInt(4, 25);
            if (c.cant + colecta > 2000) {
                c.cant = 2000;
                System.out.println("El " + Thread.currentThread().getName() + " aporta " + (2000 - c.cant));
            } else {
                c.cant += colecta;
                System.out.println("El " + Thread.currentThread().getName() + " aporta " + colecta);
            }
            System.out.println("Cantidad actual = " + c.cant);
            try {
                Thread.sleep(r.nextLong(10, 200));
            } catch (InterruptedException e) {
                System.err.println("Ha ocurrido un error con la espera");
                e.printStackTrace();
            }
        }
    }
}
