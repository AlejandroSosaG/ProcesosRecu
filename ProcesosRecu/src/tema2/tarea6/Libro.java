package tema2.tarea6;

public class Libro {
	// Creamos una lista de libros compartidos por los estudiantes.
	public static boolean[] libros = new boolean[9];

	/**
	 * Método que se encarga de bloquear dos libros mientras se están usando.
	 * @param libro1
	 * @param libro2
	 */
	public synchronized void reservaLibros( int libro1, int libro2) {
		while (libros[libro1] == true || libros[libro2] == true) {
			try {
				this.wait();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		libros[libro1] = true;
		libros[libro2] = true;
	}

	/**
	 * Este método se encarga de liberar los libros bloqueados por los alumnos.
	 * @param libro1
	 * @param libro2
	 */
	public synchronized void liberaLibros(int libro1, int libro2) {
		libros[libro1] = false;
		libros[libro2] = false;
		this.notify();
	}
}