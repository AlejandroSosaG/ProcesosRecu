package examenes.tema2.ejercicio1;

import java.util.concurrent.Semaphore;

public class Peluqueria implements Runnable {

    static Semaphore silla = new Semaphore(4);
    static Semaphore barbero = new Semaphore(2);

    public static void main(String[] args) {
        try {
            Peluqueria p = new Peluqueria();
            for (int i = 1; i <= 10; i++) {
                Thread hilo = new Thread(p);
                System.out.println(hilo.getState());
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
            boolean barberos = false;
            while (!barberos) {
                if (silla.availablePermits() > 0) {
                    silla.acquire();
                    System.out.println("El " + Thread.currentThread().getName() + " está esperando en una silla");
                    System.out.println(Thread.currentThread().getName() + Thread.currentThread().getState());
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + Thread.currentThread().getState());
                    silla.release();
                }else {
                    System.out.println("El " + Thread.currentThread().getName() + " no ha encontrado silla libre y se va");
                    System.out.println(Thread.currentThread().getName() + Thread.currentThread().getState());
                    Thread.currentThread().interrupt();
                    System.out.println(Thread.currentThread().getName() + Thread.currentThread().getState());
                }
                if (!barberos && barbero.availablePermits() > 0){
                    barbero.acquire();
                    System.out.println("El " + Thread.currentThread().getName() + " está siendo atendido por un barbero");
                    Thread.sleep((long) (Math.random() * 5000));
                    System.out.println("El " + Thread.currentThread().getName() + " ha terminado en la peluquería");
                    barbero.release();
                    barberos = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}