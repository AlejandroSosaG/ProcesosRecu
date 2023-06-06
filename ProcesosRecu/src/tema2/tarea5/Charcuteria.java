package tema2.tarea5;

import java.util.concurrent.Semaphore;

public class Charcuteria implements Runnable {
    static Semaphore carniceria = new Semaphore(4);
    static Semaphore charcuteria = new Semaphore(2);
    public static void main(String[] args) {
        try {
            Charcuteria c = new Charcuteria();
            for (int i = 1; i <= 10; i++) {
                Thread hilo = new Thread(c);
                hilo.setName("cliente " + i);
                hilo.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        try {
            boolean atendidoCarniceria = false, atendidoCharcuteria = false;
            while (!atendidoCarniceria || !atendidoCharcuteria) {
                if (!atendidoCarniceria && carniceria.availablePermits() > 0) {
                    carniceria.acquire();
                    System.out.println("El " + Thread.currentThread().getName() + " está siendo atendido en la carnicería");
                    Thread.sleep((long) (Math.random() * 5000));
                    System.out.println("El " + Thread.currentThread().getName() + " ha terminado en la carnicería");
                    carniceria.release();
                    atendidoCarniceria = true;
                }
                if (!atendidoCharcuteria && charcuteria.availablePermits() > 0){
                    charcuteria.acquire();
                    System.out.println("El " + Thread.currentThread().getName() + " está siendo atendido en la charcutería");
                    Thread.sleep((long) (Math.random() * 5000));
                    System.out.println("El " + Thread.currentThread().getName() + " ha terminado en la charcutería");
                    charcuteria.release();
                    atendidoCharcuteria = true;
                }
            }
            System.out.println("El " + Thread.currentThread().getName() + " está servido completamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}