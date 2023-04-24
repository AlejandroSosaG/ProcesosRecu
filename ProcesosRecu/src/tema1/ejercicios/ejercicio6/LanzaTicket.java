package tema1.ejercicios.ejercicio6;

import java.io.File;
import java.io.IOException;
public class LanzaTicket {
    public static void main(String[] args) {
        // Creamos la ruta del proceso que queremos lanzar.
        String []comando = {"java", ".\\src\\tema1\\ejercicios\\ejercicio6\\TicketCompra.java"};
        // Creamos el lanzador y le añadimos la ruta del proceso.
        ProcessBuilder pb = new ProcessBuilder(comando);
        // Le decimos que muestre el mensaje de entrada por pantalla.
        pb.redirectInput(ProcessBuilder.Redirect.INHERIT);
        // Le decimos que muestre el mensaje de salida en el archivo que le pasamos, sin sobreescribir lo que contiene.
        pb.redirectOutput(ProcessBuilder.Redirect.appendTo(new File(".\\src\\tema1\\ejercicios\\ejercicio6\\Ticket.txt")));
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
