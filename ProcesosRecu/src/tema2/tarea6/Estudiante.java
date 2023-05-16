package tema2.tarea6;

import java.util.Random;

public class Estudiante implements Runnable {
    // Creamos una lista de los libros que comparten los estudiantes.
    public static boolean[] libros = new boolean[9];
    // Creamos un objeto.
    public static Object o = new Object();

    public static void main(String[] args) {
        // Creamos un objeto de tipo estudiante.
        Estudiante e = new Estudiante();
        // Recorremos el bucle 4 veces.
        for (int i = 1; i <= 4; i++) {
            // Creación de hilo.
            Thread hilo = new Thread(e);
            // Le asignamos un nombre al hilo actual.
            hilo.setName("Estudiante " + i);
            // Lanzamos el hilo actual.
            hilo.start();
        }
    }

    @Override
    public void run() {
        try {
            // El alumno actual elige dos libros aleatorios.
            int libro1 = new Random().nextInt(9);
            int libro2 = new Random().nextInt(9);
            while (libro2 == libro1) {
                libro2 = new Random().nextInt(9);
            }
            // El alumno espera hasta que los libros estén disponibles.
            synchronized (o) {
                while (libros[libro1] == true || libros[libro2] == true) {
                    o.wait();
                }
                libros[libro1] = true;
                libros[libro2] = true;
            }
            // Se muestra por pantalla un mensaje con los libros reservados.
            System.out.println(Thread.currentThread().getName() + " tiene reservados los libros " + libro1 + " y " + libro2);
            // Se pausa el hilo actual entre 
            Thread.sleep((long) (Math.random() * 5000 + 3000));
            System.out.println(Thread.currentThread().getName() + " ha terminado de leer.");
            synchronized (o) {
                libros[libro1] = false;
                libros[libro2] = false;
                o.notify();
            }
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}