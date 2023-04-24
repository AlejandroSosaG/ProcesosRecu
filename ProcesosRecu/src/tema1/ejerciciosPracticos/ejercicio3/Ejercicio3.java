package tema1.ejerciciosPracticos.ejercicio3;

import java.io.IOException;

public class Ejercicio3 {
    public static void main(String[] args) {
        // Creamos las rutas de los procesos que queremos lanzar pasándoles la letra a utilizar y tambien
        // creamos los lanzadores, les añadimos las rutas de los procesos.
        String[] comando1 = {"java", "CuentaCaracteres", "a"};
        ProcessBuilder pb1 = new ProcessBuilder(comando1);
        String[] comando2 = {"java", "CuentaCaracteres", "e"};
        ProcessBuilder pb2 = new ProcessBuilder(comando2);
        String[] comando3 = {"java", "CuentaCaracteres", "i"};
        ProcessBuilder pb3 = new ProcessBuilder(comando3);
        String[] comando4 = {"java", "CuentaCaracteres", "o"};
        ProcessBuilder pb4 = new ProcessBuilder(comando4);
        String[] comando5 = {"java", "CuentaCaracteres", "u"};
        ProcessBuilder pb5 = new ProcessBuilder(comando5);
        // Les decimos a los lanzadores que muestren las entrada, salida y error por pantalla..
        pb1.inheritIO();
        pb2.inheritIO();
        pb3.inheritIO();
        pb4.inheritIO();
        pb5.inheritIO();
        try {
            long inicio = System.currentTimeMillis(); // Guardamos el tiempo de inicio.
            // Lanzamos los procesos y hacemos los hilos esperen a que terminen los procesos.
            Process p1 = pb1.start();
            p1.waitFor();
            Process p2= pb2.start();
            p2.waitFor();
            Process p3= pb3.start();
            p3.waitFor();
            Process p4= pb4.start();
            p4.waitFor();
            Process p5= pb5.start();
            p5.waitFor();
            long fin = System.currentTimeMillis(); // Guardamos el tiempo final.
            int tiempo = (int) (fin - inicio); // Guardamos el tiempo final menos el de inicio.
            // Mostramos por pantalla el tiempo que han tardado en ejecutarse todos los procesos.
            System.out.println(tiempo);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
