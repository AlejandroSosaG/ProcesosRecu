package tema2.tarea6;

import java.util.Random;

public class Libro {
	// Creamos una lista de libros compartidos por los estudiantes.
	public static boolean[] libros = new boolean[9];
	int libro1, libro2;
	public Libro() {}

	public Libro(int libro1, int libro2) {
		this.libro1 = libro1;
		this.libro2 = libro2;
	}

	/**
	 * Método que se encarga de bloquear dos libros mientras se están usando.
	 */
	public synchronized void reservaLibros() {
		while (libros[libro1] == true || libros[libro2] == true) {
			if (libros[libro1] == true){
				while (libro2 == libro1) {
					libro1 = new Random().nextInt(9);
				}
			}else if (libros[libro2] == true){
				while (libro2 == libro1) {
					libro2 = new Random().nextInt(9);
				}
			}
		}
		// Se muestra por pantalla un mensaje con los libros reservados.
		System.out.println(Thread.currentThread().getName() + " tiene reservados los libros " + libro1 + " y " + libro2);
		libros[libro1] = true;
		libros[libro2] = true;
	}

	/**
	 * Este método se encarga de liberar los libros bloqueados por los alumnos.
	 */
	public synchronized void liberaLibros() {
		libros[libro1] = false;
		libros[libro2] = false;
		this.notifyAll();
	}
}