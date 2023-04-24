package tema1.ejercicios.ejercicio11;

import java.io.IOException;

public class Ejercicio11 {
    public static void main(String[] args) throws IOException {
        // Creamos las rutas de los archivos que queremos utilizar.
        String[] comando1 = {"cmd", "/C", "type", "null", ">", "C:\\pruebas\\carpeta1\\fichero2.txt"};
        String[] comando2 = {"Notepad.exe", "C:\\pruebas\\carpeta1\\fichero1.txt"};
        // Creamos los lanzadores, les añadimos las rutas de los procesos y les decimos que muestren las entrada, salida y error por pantalla.
        ProcessBuilder pb1 = new ProcessBuilder(comando1);
        pb1.inheritIO();
        ProcessBuilder pb2 = new ProcessBuilder(comando2);
        pb2.inheritIO();
        try {
            // Lanzamos los procesos y hacemos los hilos esperen a que terminen los procesos.
            Process p1 = pb1.start();
            p1.waitFor();
            Process p2 = pb2.start();
            p2.waitFor();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
