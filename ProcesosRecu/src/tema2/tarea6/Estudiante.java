package tema2.tarea6;

import java.util.Random;

public class Estudiante implements Runnable {
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
            // Creamos un nuevo libro.
            Libro libro = new Libro();
            // El alumno actual elige dos libros aleatorios.
            int libro1 = new Random().nextInt(9);
            int libro2 = new Random().nextInt(9);
            while (libro2 == libro1) {
                libro2 = new Random().nextInt(9);
            }
            // LLamamos al método reservaLibros de la clase Libro para usar 2 libros distintos.
            libro.reservaLibros(libro1, libro2);
            // Se pausa el hilo actual entre 3 y 5 segundos.
            Thread.sleep((long) (Math.random() * 5000 + 3000));
            // Mostramos por pantalla un mensaje diciendo que el estudiante actual ha acabado.
            System.out.println(Thread.currentThread().getName() + " ha terminado de leer.");
            // LLamamos al método liberaLibros de la clase Libro para liberar los 2 libros usados.
            libro.liberaLibros(libro1, libro2);
            // Esperamos un segundo.
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}