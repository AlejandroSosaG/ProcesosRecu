package tema1.ejercicios.ejercicio4;

import java.io.File;
import java.io.IOException;

public class LanzaLento {
    public static void main(String[] args) {
        // Guardamos la ruta del proceso que queremos lanzar.
        String comando[] = {"java", "C:\\Users\\asosa\\Documents\\ProcesosRecu\\src\\tema1\\ejercicios\\ejercicio2\\EjemplosLento.java"};
        // Creamos el lanzador y le añadimos la ruta del proceso.
        ProcessBuilder pb = new ProcessBuilder(comando);
        // Le añadimos el directorio donde se trabajará.
        pb.directory(new File("C:\\Users\\asosa\\Documents\\ProcesosRecu"));
        // Le decimos que muestre el mensaje de salida en el archivo que le pasamos.
        pb.redirectOutput(new File("salidaProcesoLento"));
        // Le decimos que muestre el mensaje de error por pantalla.
        pb.redirectError(ProcessBuilder.Redirect.INHERIT);
        try {
            // Lanzamos el proceso.
            Process p = pb.start();
            // Mientras el proceso se ejecute mostramos un mensaje por pantalla.
            while(p.isAlive()) {
                System.out.println("Sigue en ejecución");
                // Hacemos que el hilo actual espere 3 segundos.
                Thread.sleep(3000);
            }
            // Mostramos por pantalla un mensaje de que ha terminado.
            System.out.println("El proceso ha terminado");
        }catch (IOException e){
            System.err.println("Ha habido un error al lanzar el proceso");
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
