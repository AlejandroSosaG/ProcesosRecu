package tema1.ejerciciosPracticos.ejercicio3;

import java.io.IOException;

public class Ejercicio3b {
    public static void main(String[] args) {
        // Creamos las rutas de los procesos que queremos lanzar pasándoles la letra a utilizar y tambien
        // creamos los lanzadores, les añadimos las rutas de los procesos.
        String[] comando1 = {"java", "C:\\Users\\asosa\\Documents\\ProcesosRecu2-master\\ProcesosRecu\\src\\tema1\\ejerciciosPracticos\\ejercicio3\\CuentaCaracteres.java", "a"};
        ProcessBuilder pb1 = new ProcessBuilder(comando1);
        String[] comando2 = {"java", "C:\\Users\\asosa\\Documents\\ProcesosRecu2-master\\ProcesosRecu\\src\\tema1\\ejerciciosPracticos\\ejercicio3\\CuentaCaracteres.java", "e"};
        ProcessBuilder pb2 = new ProcessBuilder(comando2);
        String[] comando3 = {"java", "C:\\Users\\asosa\\Documents\\ProcesosRecu2-master\\ProcesosRecu\\src\\tema1\\ejerciciosPracticos\\ejercicio3\\CuentaCaracteres.java", "i"};
        ProcessBuilder pb3 = new ProcessBuilder(comando3);
        String[] comando4 = {"java", "C:\\Users\\asosa\\Documents\\ProcesosRecu2-master\\ProcesosRecu\\src\\tema1\\ejerciciosPracticos\\ejercicio3\\CuentaCaracteres.java", "o"};
        ProcessBuilder pb4 = new ProcessBuilder(comando4);
        String[] comando5 = {"java", "C:\\Users\\asosa\\Documents\\ProcesosRecu2-master\\ProcesosRecu\\src\\tema1\\ejerciciosPracticos\\ejercicio3\\CuentaCaracteres.java", "u"};
        ProcessBuilder pb5 = new ProcessBuilder(comando5);
        // Les decimos a los lanzadores que muestren las entrada, salida y error por pantalla..
        pb1.inheritIO();
        pb2.inheritIO();
        pb3.inheritIO();
        pb4.inheritIO();
        pb5.inheritIO();
        try {
            long inicio = System.currentTimeMillis(); // Guardamos el tiempo de inicio.
            // Lanzamos los procesos.
            Process p1 = pb1.start();
            Process p2= pb2.start();
            Process p3= pb3.start();
            Process p4= pb4.start();
            Process p5= pb5.start();
            // Esperamos a que terminen los distintos hilos para recoger el tiempo final.
            p1.waitFor();
            p2.waitFor();
            p3.waitFor();
            p4.waitFor();
            p5.waitFor();
            long fin = System.currentTimeMillis(); // Guardamos el tiempo final.
            int tiempo = (int) (fin - inicio); // Guardamos el tiempo final menos el de inicio.
            // Mostramos por pantalla el tiempo que han tardado en ejecutarse todos los procesos.
            System.out.println(tiempo);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}