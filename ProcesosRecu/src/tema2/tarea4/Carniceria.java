package tema2.tarea4;

import java.util.concurrent.Semaphore;

public class Carniceria implements Runnable{
    static Semaphore semaforo = new Semaphore(4);

    public static void main(String[] args) {
        Carniceria c = new Carniceria();
        for (int i = 1; i <= 10; i++) {
            Thread hilo = new Thread(c);
            hilo.setName("cliente "+ i);
            hilo.start();
        }
    }
    @Override
    public void run() {
        try {
            semaforo.acquire();
            System.out.println("El " + Thread.currentThread().getName() + " está siendo atendido");
            Thread.sleep((long) (Math.random()*10000));
            System.out.println("El " + Thread.currentThread().getName() + " ha terminado en la carnicería");
            semaforo.release();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
