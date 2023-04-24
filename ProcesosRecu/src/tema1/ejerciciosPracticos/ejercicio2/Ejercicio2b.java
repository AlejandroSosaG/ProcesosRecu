package tema1.ejerciciosPracticos.ejercicio2;

import java.io.IOException;

public class Ejercicio2b {
    public static void main(String[] args) throws InterruptedException {
        // Creamos las rutas de los archivos que queremos utilizar.
        String[] comando1 = {"Notepad.exe",  "C:\\ejercicio2\\numLineas.txt"};
        String[] comando2 = {"java", ".\\ProcesosRecu\\src\\tema1\\ejerciciosPracticos\\ejercicio2\\CuentaLineas.java"};
        // Creamos los lanzadores, les añadimos las rutas de los procesos y les decimos que muestren las entrada, salida y error por pantalla.
        ProcessBuilder pb1 = new ProcessBuilder(comando1);
        ProcessBuilder pb2 = new ProcessBuilder(comando2);
        pb1.inheritIO();
        pb2.inheritIO();
        try {
            // Lanzamos el primer proceso.
            Process p1 = pb1.start();
            int segundos = 0; // En esta variable guardaremos el tiempo que se está ejecutando el proceso.
            // Mientras el proceso se ejecute y segundos sea menor a 30 aumentamos segundos y hacemos que el
            // hilo espere 1 segundo.
            while (p1.isAlive() && segundos<30 ){
                segundos++;
                Thread.sleep(1000);
            }
            // Si la cantidad de segundos es mayor o igual a 30 se destruye el proceso 1 se muestra un mensaje.
            if (segundos >= 30){
                p1.destroy();
                System.err.println("El proceso se ha cerrado");
            }
            // Lanzamos el segundo proceso.
            pb2.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
