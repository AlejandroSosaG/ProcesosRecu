package tema1.ejercicios.ejercicio5;

import java.io.File;
import java.io.IOException;

public class LanzaPreguntaNombre {
    public static void main(String[] args) {
        // Creamos la ruta del proceso que queremos lanzar.
        String []comando = {"java", "C:\\Users\\asosa\\Documents\\ProcesosRecu\\src\\tema1\\ejercicios\\ejercicio5\\PreguntaNombre.java"};
        // Creamos el lanzador y le añadimos la ruta del proceso.
        ProcessBuilder pb = new ProcessBuilder(comando);
        // Le decimos que muestre el mensaje de entrada en el archivo que le pasamos.
        pb.redirectInput(new File(".\\src\\tema1\\ejercicios\\ejercicio5\\nombre.txt"));
        // Le decimos que muestre el mensaje de salida por pantalla.
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        // Le decimos que muestre el mensaje de error por pantalla.
        pb.redirectError(ProcessBuilder.Redirect.INHERIT);
        try {
            // Lanzamos el proceso.
            Process p = pb.start();
            // Hacemos que el hilo actual espere a que el proceso termine.
            p.waitFor();
        }catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
