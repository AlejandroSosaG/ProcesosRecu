package examenes.tema2.ejercicio2;

import java.util.Random;

public class Colecta  implements Runnable{
    static int cant = 0;
    public static void main(String[] args) {
        Colecta c = new Colecta();
        for (int i = 1; i <= 10; i+= 3) {
            Thread hilo = new Thread(c);
            hilo.setName("recolector " + i);
            hilo.setPriority(i);
            hilo.start();
        }
//        for (int i = 1; i <= 4; i++) {
//            Thread hilo = new Thread(c);
//            hilo.setName("consumidor " + i);
//            hilo.start();
//        }
    }
    @Override
    public void run(){
        int colecta = 0;
        while (cant < 2000){
            colecta = new Random().nextInt(4, 25);
            if (cant + colecta >2000){
                cant = 2000;
                System.out.println("El " + Thread.currentThread().getName() + " aporta " + (2000 - cant));
                System.out.println("Cantidad actual = " + cant);
            }else{
                cant += colecta;
                System.out.println("El " + Thread.currentThread().getName() + " aporta " + colecta);
                System.out.println("Cantidad actual = " + cant);
            }
            try {
                Thread.sleep(new Random().nextLong(10, 200));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        while (cant < 2000){
//            colecta = (int) Math.random()*40+10;
//            if (colecta >= cant){
//                cant -= colecta;
//                System.out.println("El " + Thread.currentThread().getName() + " recoje " + colecta);
//                System.out.println("Cantidad actual = " + cant);
//            }
//            try {
//                Thread.sleep((int) Math.random()*3000+200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
